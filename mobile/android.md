# Android Build App Note

## Debug, Release Apk

#### Keytool

```bash
   # Get debug fingure (sha1...)
   keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android

   # List all keys in library_android/keystore.jks
   keytool -keystore keystore.jks -list

   # List all release fingures (sha1...)
   keytool -keystore keystore.jks -list -v

   # Get release fingure (sha1...) from a key alias (pwd: keystore.jks!)
   keytool -keystore keystore.jks -list -v -alias your_key_alias

   # Change keystore.jks password (pwd: keystore.jks!)
   keytool -keystore keystore.jks -storepasswd

   # Delete an alias entry from keystore.jks
   keytool -keystore keystore.jks -delete -alias your_key_alias
```

## Firebase

#### Firebase analytics

- Online debug (in debugView)

```bash
	>> start debug
	adb shell setprop debug.firebase.analytics.app package_name
	>> stop debug
	adb shell setprop debug.firebase.analytics.app .none.
```
