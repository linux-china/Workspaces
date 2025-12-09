Workspaces plugin
=================

Workspaces, a plugin for JetBrains IDEs, enables you to create and manage named groups of files(workspace)  within a
project. https://plugins.jetbrains.com/plugin/21829-workspaces

<!-- Plugin description -->

Migrated from unmaintained https://plugins.jetbrains.com/plugin/83-workspaces, and thanks for Chris Bartley's work.

This plugin enables you to create and manage named groups of files, or "workspaces", within a project.
You can bind a name to a set of one or more files and then open/close that set of files as a group.
You can create as many workspaces as you want and arrange them in the Workspaces tool window in any order you like.

### Use cases:
                                                       
* AI Agent: read group of files and generate code
* GitHub Copilot: read current file and any files opened in your IDE
* Domain Driven Design: group entity, repository, service, controller, etc. together
* Frontend development: group view, components, API service layer, etc. together
* Database development: group SQL, JPA interface, MyBatis Mapper interface, XML Mapper files, unit tests, etc. together

### Tips:

* Please commit `.idea/workspaces.xml` into git and share workspace items with your team members.
* You can select multi workspace items in Workspaces toolwindow and execute operations.

<!-- Plugin description end -->

# References

* IntelliJ Platform Plugin Template: https://github.com/JetBrains/intellij-platform-plugin-template
