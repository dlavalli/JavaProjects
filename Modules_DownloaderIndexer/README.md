# NOTES

- Since this is an aggregator project, you cannot have a src/main/java as executor for the jar as it would only generate a .jar file for each module. One possibility is to declare a test module that requires all other modules and export nothing. The other is to declare test src code and put the testing there

<br>

- To manually compile do: 
```
javac -d modules --module-source-path "./*/src/main/java" -module util.module,downloader.module,indexer.module
```
<br>
- To then run do:
```
java -p modules -m indexer.module/com.lavalliere.daniel.jpms.indexer.Indexer
```




