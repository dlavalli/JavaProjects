# NOTES

- Since this is an aggregator project, you cannot have a src/main/java as executor for the jar as it would only generate a .jar file for each module. One possibility is to declare a test module that requires all other modules and export nothing. The other is to declare test src code and put the testing there. Also note that for this example, simple module names are used but to avoid collision with other modules, it is customary to name module with names similar to the package name, e.g. **com.semanticsquare.downloader instead of downloader.module**.

- Note that in case of downloader.module, indexer.module and util.module, the packages are distinct (they cannot be identical or child/parent of each other) to avoid the split package error that will be raised at compile time if that is the case.
- In this version, an unnamed module, crawler is added that will use the other modules. In intellij you can do this by creating a dedicated module (ie: subproject) but NOT defining a module-info.java file in it. This will make it an unnamed module that can use the other modules. In this case, the crawler module is used to test the other modules. The crawler module is not part of the aggregator project and is not compiled with the other modules. It is only used to test the other modules. The crawler module can be run from the command line using the following command:

<br>

- To manually compile do:
```
javac -d modules --module-source-path "./*/src/main/java" --module jpms.util.module,jpms.downloader.module,jpms.indexer.module
```
<br> 

- To then run the indexer's main class, do:
```
java -p modules -m jpms.indexer.module/com.lavalliere.daniel.jpms.indexer.Indexer
NOTE: The  --p option must come before the -m option
```
<br>

- To run the main class from the unnamed module (you have to tell it where to find your modules with --add-modules), do:
```
java -cp .\jpms.crawler.unnamed.module\target\jpms.crawler.unnamed.module-1.0-SNAPSHOT.jar -p modules --add-modules jpms.indexer.module,jpms.downloader.module,jpms.util.module com.lavalliere.daniel.jpms.crawler.Crawler
```

<br>

- To manually create a module jar, store it under lib/downloader.jar using the compiled module under out/com.semanticsquare.downloader, do
```
jar -cf lib/downloader.jar -C out/com.semanticsquare.downloader .
```
<br> 

- To view the content of generated module jars

```
cd .\.jpms.indexer.module\target\
jar tvf .\jpms.indexer.module-1.0-SNAPSHOT.jar
```


