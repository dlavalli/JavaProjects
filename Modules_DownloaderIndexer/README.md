# NOTES

- Since this is an aggregator project, you cannot have a src/main/java as executor for the jar as it would only generate a .jar file for each module. One possibility is to declare a test module that requires all other modules and export nothing. The other is to declare test src code and put the testing there

- Note that in case of downloader.module, indexer.module and util.module, the packages are distinct (they cannot be identical or child/parent of each other) to avoid the split package error that will be raised at compile time if that is the case.

<br>

- To manually compile do: 
```
javac -d modules --module-source-path "./*/src/main/java" --module util.module,downloader.module,indexer.module
```
<br> 

- To then run do:
```
java -p modules -m indexer.module/com.lavalliere.daniel.jpms.indexer.Indexer
NOTE: The  --p option must come before the -m option
```



