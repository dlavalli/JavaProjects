# NOTES

- Since this is an aggregator project, you cannot have a src/main/java as executor for the jar as it would only generate a .jar file for each module. One possibility is to declare a test module that requires all other modules and export nothing. The other is to declare test src code and put the testing there. Also note that for this example, simple module names are used but to avoid collision with other modules, it is customary to name module with names similar to the package name, e.g. **com.semanticsquare.downloader instead of downloader.module**.

- Note that in case of downloader.module, indexer.module and util.module, the packages are distinct (they cannot be identical or child/parent of each other) to avoid the split package error that will be raised at compile time if that is the case.

<br>

- To manually compile do: 
```
javac -d modules --module-source-path "./*/src/main/java" --module jpms.util.module,jpms.downloader.module,jpms.indexer.module
```
<br> 

- To then run do:
```
java -p modules -m jpms.indexer.module/com.lavalliere.daniel.jpms.indexer.Indexer
NOTE: The  --p option must come before the -m option
```
- To manually create a module jar, store it under lib/downloader.jar using the compiled module under out/com.semanticsquare.downloader, do
```
jar -cf lib/downloader.jar -C out/com.semanticsquare.downloader .
```
<br> 

- To view the content of generated module jars. 
```
cd .\indexer.module\target\
jar tvf .\indexer.module-1.0-SNAPSHOT.jar
```


