# Changelog

## 0.1.0 - 2025-09-30
- Introduce the `Terminator` coordinator with blocking and non-blocking service support plus interrupt-aware shutdown orchestration.
- Provide `BlockingTerminable`, `NonBlockingTerminable` and `TerminationException` APIs.
- Add JUnit 5 + AssertJ regression tests covering termination ordering, timeouts and failure aggregation.
- Configure Gradle publishing for Maven Local and GitHub Packages alongside a GitHub Actions build on Temurin JDK 21.
