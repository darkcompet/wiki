# Bash Documentary

## Command list

#### Move, Jump

- `cd`: Change directory

```bash
   cd [dir_name]
   # Goto user home directory
   cd ~
   # Goto system root directory
   cd /
```

#### Permission and Grouping

- `chown`: Change file owner. To see current owner, use `whoami`.

```bash
   # Change owner of a file to specified name and group
   sudo chown [username] [filename]

   # Change owner of a directory recursively to specified name and group
   sudo chown -R [username]:[group] [dirname]

   # Change only username, just omit group
   sudo chown [username]: [filename]
   # or change only username
   sudo chown :[group] [filename]
```

#### File Action

- `cp`: Copy file in a machine

```bash
   cp -r [src_dir] [dst_dir]
```

- `scp`: Copy file from remote machine to local machine

```bash
   # -r means recursively copy for directory. Omit it to copy file
   scp -r [remote_user]@[remote_url]:[remote_path_to_dir] [path_to_local_dir]
```

- `mv`: Move file

```bash
   # Move a file to a folder
   mv [file_name] [dir_name]
```

- `rm`: Delete a file permanently (maybe impossible to recovery)

```bash
   # Delete file
   rm [file_name]
   # Delete folder
   rm -r [dir_name]
```

#### Search

- `find`: Find all files in a directory

```bash
	# Find doc.txt file in current directory
	find . -name "doc.txt"
	# Find doc.txt file in current directory and delete if exist
	find . -name "doc.txt" -delete
```

- `ls`: List files

```bash
	# List all files inside folder directory
	ls folder/
```

1.1) Create and install project
android create project --target 1 --package com.condroid --name Condroid --activity CondroidActivity --path ./
ant debug
adb -d install -r bin/MyApp.apk

1.2) Enable debug over wifi
adb kill-server
adb start-server
adb tcpip 5555
adb shell netcfg | grep wlan0
adb connect device_ip_address:5555

3) push, pull data
adb devices
adb pull /data/ /Volumes/Storage/Downloads/
adb push

2) convert
2.1) Convert gif to png
convert -coalesce input_file.gif output_file.png

2.2) Convert jpg to png
convert input_file.jpg -resize number_of_percent output_file.png

3) createinstallmedia
3.1) Create bootable installation for Mac OSX
sudo /Volumes/Storage/Apps/apple/yosemite.app/Contents/Resources/createinstallmedia --volume /Volumes/USB --applicationpath /Volumes/Storage/Apps/apple/yosemite.app --nointeraction

4) chown
4.1) Change user permission
sudo chown -R user_name file_name

5) diskutil
5.1) eject device
diskutil eject dev_name

6) ditto
6.1) compare 2 folders
ditto -V folder_1 folder_2

7) defaults
7.1) show, hide all files
defaults write com.apple.finder AppleShowAllFiles YES
killall -kill Finder

8) emacs
8.1) prevent emacs creates backup file
# save below to ~/.emacs to prevent emacs create backup file
setq backup-inhibited t
setq auto-save-default nil

9) echo
9.1) Clean previous line
echo -n R | tr 'R' '\r' ;

10) find
10.1) Search specified file
find dir_name | grep -i file_name

11) git
11.1) create new git repository
git init

11.2) clone repo to other location
git clone src_repo des_dir
# or, clone to current directory
git clone src_repo

11.3) push new files or folders to a branch. Note that, git pushes only to the branch with same name already exist there.
git init
git add file_or_dir_name
git commit -m "commit_message"
git remote add origin https://github.com/vuancoal/repo_name.git
git push -u origin master

11.4) generate ssh-key
ssh-keygen

11.5) view status of changes
git status -s

11.6) view log
git log

11.7) view added file by SHA-1 key. Note that, SHA-1 key are retrieved by executing command "git log".
git show sha_key

11.8) update repo
git pull

11.9) stash the changes
git stash
# after that, place them in the current working
git stash pop
# view list of stashed changes
git slash list

11.10) remove files
git rm file_name

11.11) revert uncommitted changes (obtain deleted (D file_name) from local repo)
git checkout HEAD deleted_file_name

12) gnuplot
12.1) plot graph from data file
set terminal postscript eps enhanced
set xlabel "x"
set ylabel "y"
set xrange [-10:10]
set yrange [-20:20]
set bmargin 10
set style line 1 lc rgb 'black' pt 5 ps 1
set style line 2 lc rgb 'black' pt 7 ps 1
set style line 3 lc rgb 'black' pt 9 ps 1
set key top center
show xlabel
show ylabel
plot "data1.dat" title 'title1' with linespoints ls 1, "data2.dat" title "data2" with linespoints ls 2
set output "out.eps"
replot
q

12.2) approximate data with some function
f(x) = a * x + b
fit f(x) "file.dat" via a, b

12.3) animation on xQuarzt
for((i = -50; i < 50; ++i)); do echo -e "set yrange [-50:50]; pl $i*sin(x) \n"; done | gnuplot

13) javac, java, jar, jdb, jconsole
13.1) execute jar file
java -jar file_name.jar
# run a specified class in jar file
java -cp file_name.jar pkg_name.class_name

13.2) compile and run
javac pkg_name/main_file_name.java
java pkg_name.main_file
# or, compile with external libraries on Unix (replace ':' with ';' on Windows).
# and it is needed to copy manually all resource bundles to binary output folder
javac -cp .:path_to_external_lib1.jar:path_to_external_lib2.jar pkg_name.main_file.java
java -cp .:path_to_external_lib1.jar:path_to_external_lib2.jar pkg_name.main_file

13.3) enable assertion
java -ea pkg_name.file_name

13.4) create file.war
jar -cvf file_name.war classes_dir

13.5) create jar file
# create Manifest.txt file with the following content
Manifest-Version: 1.0
Created-By: 1.7.1991 (VuanCoal)
Main-Class: pkg_name.main_file
Class-Path: relative_path_to_external_lib.jar relative_path_from_current_dir_to_external_lib.jar
# execute below to create jar file
jar cfm output_file.jar Manifest.txt pkg_name/*

13.6) open JVM gui
jconsole

14) ln
14.1) Link folder
sudo ln -s source_file file_pointer

15) lsregister
15.1) remove duplicated app in open with
lsregister -kill -r -domain local -domain system -domain user

16) mencoder
16.1) create avi file
mencoder "mf://*.png" -mf fps=30 -ovc lavc -lavcopts vcodec=msmpeg4v2 -o output_file.avi

16.2) play avi file
mplayer file_name.avi

17) mkfile
17.1) create new file with specified size
mkfile -n _10m file_name

18) meteor
18.1) create new app
meteor create app_name

18.2) run created app, and look at it at localhost:30000
cd app_name
meteor npm install
meteor

18.3) build an app on android device
# must setup at first
# meteor install-sdk android
# meteor add-platform android
# meteor run android
meteor run android-device

19) nkf
19. 1) Convert character code
nkf cmd_option file_name > output_file.txt
-b	バッファリング出力を行う
-u	出力時にバッファリングを行わない
-j	JISコードに変換する
-e	EUCコードに変換する
-s	シフトJISコードに変換する
-w	UTF8コードに変換する
-i?	JIS漢字を指示するシーケンスとして ESC-'$'-?を使用する
-o?	1バイト英数文字セットを指示するシーケンスとしてESC-'('-?を使用する
-r	ROT13/47の変換する
-T	テキスト・モードで出力する
-l	0x80-0xfeのコードをISO-8859-1 (Latin-1)として扱う．ただし，JISコードの時のみ有効
-f?	一行?文字になるように簡単な整形を行う
-Z	X0208中の英数字と一部の記号をASCIIに変換する
-J	JIS(ISO-2022-JP)と仮定して処理する
-E	日本語EUCと仮定して処理する
-S	シフトJISと仮定して処理する
-X	シフトJISと仮定して処理する．ただし，X0201仮名があるものとする
-B	壊れた（ESCが欠損した）JISと仮定して処理する．-B1の場合はESC-(およびESC-$)
のあとのコードを問わない．-B2の場合は改行の後に強制的にASCIIに戻す
-x	通常行われるX0201仮名->X0208の仮名変換しない

20) oascript
20.1) set volume
sudo osascript -e "set Volume volume_value"

20.2) sleep, logout, restart, shutdown
pmset sleepnow
osascript -e 'tell app "System Events" to log out'
osascript -e 'tell app "loginwindow" to «event aevtrrst»'
osascript -e 'tell app "loginwindow" to «event aevtrsdn»'

20.3) alert dialog
osascript -e 'tell app "System Events" to display dialog "msg_to_alert"'
or,
osascript -e 'tell app "Finder" to display dialog "msg_to_alert"'

21) python
21.1) start local server at specified port
python -m SimpleHTTPServer 8000

22) read
22.1) Read input from terminal
read -p text_to_display store_var_name

23) ruby
23.1) Install hombrew
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

24) scp
24.1) Transfer file to another computer's user
scp file_to_transfer user@destination_computer:~Desktop

25) screencapture
25.1) capture screen
screencapture output_file

26) tlmgr
sudo tlmgr update --self
sudo tlmgr update --all

27) type
27.1) display function content in bash file
type function_name

28) youtube-dl
28.1) download youtube video
youtube-dl -x download_link
