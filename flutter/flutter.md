# Flutter

## Build, Run app on device or simulator

```bash
   # create new app
   flutter create --template=app -i swift -a java --androidx --org [pkgname_com.kilobytes] --description '[Pattern Detector]' [appname_pattern_detector]

   # clear debug process-files
   flutter clear

   # run app
   flutter run

   # get packages in pubspec.yaml
   flutter packages get

   # upgrade packages to latest version in pubspec.yaml
   flutter packages upgrade
```

## Plugin, Package, Module

> Refer: https://flutter.dev/docs/development/packages-and-plugins/developing-packages

#### Package types

Packages can contain several kinds of content:

- Dart packages: General packages written in Dart, for example the path package. Some of these might contain Flutter specific functionality and thus have a dependency on the Flutter framework, restricting their use to Flutter only, for example the fluro package.

- Plugin packages: A specialized Dart package which contains an API written in Dart code combined with a platform-specific implementation for Android (using Java or Kotlin), and/or for iOS (using ObjC or Swift). A concrete example is the battery plugin package.

#### Develop packages

- Develop Dart packages:

```bash
    flutter create --template=package [core]
```

- Develop Plugin packages:

```bash
   flutter create --org [com.kilobytes] --template=plugin [core]
```

## Reference
