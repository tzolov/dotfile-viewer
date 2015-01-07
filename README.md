# dotfile-viewer
Solution to render Graphviz on the web. Underneet it uses [Spring Boot](http://projects.spring.io/spring-boot/) and [Polymer](https://www.polymer-project.org/).

## Usage
Build the project with 
```
mvn clean install
```
You can also download a prebuild [dotfile-viewer-0.0.1.jar](https://www.dropbox.com/s/9clhiihu8bcwxd1/dotfile-viewer-0.0.1.jar):

```
java -jar dotfile-viewer-0.0.1-SNAPSHOT.jar --dotfiles.folder=<path to the folder with dot files>
```
`--dotfiles.folder` - specifies the path to the folder containing the dot files.
The default server port is 8080 but you can alter this with the `--server.port=<your port>` command property.

Open [http://localhost:8080](http://localhost:8080) in the browser: 

<img src="https://raw.githubusercontent.com/tzolov/dotfile-viewer/master/doc/Screen%20Shot%202015-01-07%20at%205.22.47%20PM.png" alt="alt text" width="400">

Use the filter control to filter in only the desired files.
