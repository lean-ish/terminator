# Terminator

Terminator is a small Java library that coordinates the orderly shutdown of heterogeneous services. It understands two kinds of services:

* **Blocking services** (`BlockingTerminable`) that finish all work before returning from `terminate()`.
* **Non-blocking services** (`NonBlockingTerminable`) that initiate shutdown in the background and expose `awaitTermination(Duration)` to finish up.

The coordinator makes sure non-blocking services are signalled first and waited on, while blocking services are stopped afterwards in reverse registration order. It also keeps you honest about interrupts and timeout handling so shutdown code stays responsive.

## Key features

- Register any number of blocking or non-blocking services using distinct marker interfaces.
- Initiate shutdown once with `Terminator#terminate()`, or call it repeatedly without side effects.
- Wait for the async tail of shutdown to finish with `awaitTermination(Duration)`; negative durations are treated as zero, and a zero wait is still passed through to the services.
- Aggregates failures thrown during termination, keeping the first as the cause and suppressing the rest.
- Propagates interrupts consistently during both `terminate()` and `awaitTermination()`.

## Quick start

Add the dependency to your Gradle build (replace the version with the one you publish):

```groovy
dependencies {
    implementation 'io.github.leanish:terminator:0.1.0-SNAPSHOT'
}
```

### Implement services

```java
final class HttpServer implements BlockingTerminable {
    private final Server server;

    HttpServer(Server server) {
        this.server = server;
    }

    @Override
    public void terminate() throws InterruptedException {
        server.stop(); // blocks until fully stopped
    }
}

final class MetricsReporter implements NonBlockingTerminable {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final CountDownLatch shutdown = new CountDownLatch(1);

    @Override
    public void terminate() {
        executor.submit(() -> {
            try {
                flushAndClose();
            } finally {
                shutdown.countDown();
                executor.shutdown();
            }
        });
    }

    @Override
    public boolean awaitTermination(Duration timeout) throws InterruptedException {
        return shutdown.await(timeout.toNanos(), TimeUnit.NANOSECONDS);
    }
}
```

### Coordinate shutdown

```java
Terminator terminator = new Terminator();
terminator.register(new HttpServer(server));
terminator.register(new MetricsReporter());

try {
    terminator.terminate();
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}

boolean finished = terminator.awaitTermination(Duration.ofSeconds(5));
if (!finished) {
    // log or trigger fallback action
}
```

## Building & testing

This project uses Gradle 8 and Java 21:

```bash
./gradlew build   # compile, test, package
./gradlew test    # run the test suite
```

To regenerate the API documentation:

```bash
./gradlew javadoc
```

## Contributing

Issues and pull requests are welcome. Please run `./gradlew test` locally before submitting changes.

## License

This project is distributed under the terms of the [MIT License](LICENSE).
