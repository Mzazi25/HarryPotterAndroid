fastlane documentation
----

# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```sh
xcode-select --install
```

For _fastlane_ installation instructions, see [Installing _fastlane_](https://docs.fastlane.tools/#installing-fastlane)

# Available Actions

## Android

### android ktlintFormat

```sh
[bundle exec] fastlane android ktlintFormat
```

Runs ktlintFormat on the codebase

### android ktlintCheck

```sh
[bundle exec] fastlane android ktlintCheck
```

Runs ktlintCheck on the codebase

### android detekt

```sh
[bundle exec] fastlane android detekt
```

Runs detekt on the codebase

### android test

```sh
[bundle exec] fastlane android test
```

Runs tests on the codebase

### android branch

```sh
[bundle exec] fastlane android branch
```

Builds on all branches

### android main

```sh
[bundle exec] fastlane android main
```

Runs on main branch

----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.

More information about _fastlane_ can be found on [fastlane.tools](https://fastlane.tools).

The documentation of _fastlane_ can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
