#!/bin/sh
cd "$(dirname "$0")"
java -jar dist/PacMan.jar
printf '\nPress Return to close this window.'
read answer
