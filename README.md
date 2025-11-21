# EXPath Package Repository Maven Plugin

[![Build Status](https://dl.circleci.com/status-badge/img/gh/evolvedbinary/expath-package-repository-plugin/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/evolvedbinary/expath-package-repository-plugin/tree/main)
[![Java 8](https://img.shields.io/badge/java-8-blue.svg)](https://adoptopenjdk.net/)
[![License](https://img.shields.io/badge/license-LGPL%202.1-blue.svg)](https://www.gnu.org/licenses/lgpl-2.1.html)
[![Maven Central](https://img.shields.io/maven-central/v/com.evolvedbinary.maven.plugins/expath-package-repository-plugin?logo=apachemaven&label=maven+central&color=green)](https://central.sonatype.com/search?namespace=com.evolvedbinary.maven.plugins)

A Maven plugin for downloading EXPath Packages from an EXPath Package Repository.


## Example Use

For example if you wanted to download the latest version of the `functx` and `markdown` packages from the eXist-db Public Repository for Elemental version 6.4.0, you would place the following in your `pom.xml` file:

```xml
<plugin>
    <groupId>com.evolvedbinary.maven.plugins</groupId>
    <artifactId>expath-package-repository-plugin</artifactId>
    <version>1.3.0</version>
    <executions>
        <execution>
            <id>fetch-xars</id>
            <phase>package</phase>
            <goals>
                <goal>resolve</goal>
            </goals>
            <configuration>
                <repoUri>http://exist-db.org/exist/apps/public-repo</repoUri>
                <elementalVersion>6.4.0</elementalVersion>
                <packages>
                    <package>
                        <abbrev>functx</abbrev>
                    </package>
                    <package>
                        <abbrev>markdown</abbrev>
                    </package>
                </packages>
            </configuration>
        </execution>
    </executions>
</plugin>
```
