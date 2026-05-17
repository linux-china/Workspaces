<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# jbang-idea-plugin Changelog

## [Unreleased]

## [0.14.10]

### Fixed

- Fix some NPE problems


## [0.14.9]

### Added

- Remove virtual files with `mock://` protocol from workspace, such as ToolWindow as editor

## [0.14.8]

### Fixed

- Compatible with JetBrains 2026.1

## [0.14.7]

### Fixed

- Fix NPE of workspace with deleted virtual files: marked as RED

## [0.14.6]

### Fixed

- Compatible bugs with IntelliJ IDEA 2025.3

## [0.14.5]

### Fixed

- Compatible bugs with IntelliJ IDEA 2025.1

## [0.14.3]

### Fixed

- API compatible bugs with IntelliJ IDEA 2024.1

## [0.14.2]

### Added

- Compatible with IntelliJ IDEA 2024.1

## [0.14.1]

### Added

- Multi files append to workspace support by right click files in project view.

## [0.14.0]

### Added

- Append current file to workspace by right click file tab.
- Append current file to workspace by right click file in project view.
- Close all editor tabs and open current workspaces.

### Changed

- Adopt IntelliJ Platform Plugin Template to build project
- Restructure project with project service, toolwindow, applicationConfigurable components
- Compatible with IntelliJ IDEA 2023.*
