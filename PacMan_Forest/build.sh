#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p build/classes
find src -name '*.java' > build/sources.txt
java com.sun.tools.javac.Main --release 17 -d build/classes @build/sources.txt
cp -R resourcesP/. build/classes/
java sun.tools.jar.Main --create --file dist/PacMan.jar --main-class mainGame.Main -C build/classes .
