#!/bin/sh

javac Ostclient.java
jar cmf Manifest.txt ostclient.jar Ostclient.class
