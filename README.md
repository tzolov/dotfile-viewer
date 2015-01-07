# dotfile-viewer
Solution to render Graphviz on the web. Underneet it uses [Spring Boot](http://projects.spring.io/spring-boot/) and [Polymer](https://www.polymer-project.org/).

## Usage
Build the project: 
```
mvn clean install
```
and run the embedded Tomcat server with the dotfile viewer application:
```
java -jar target/dotfile-viewer-0.0.1-SNAPSHOT.jar --dotfiles.folder=<path to the folder with dot files>
```
*Also you can also download a prebuild  [dotfile-viewer-0.0.1.jar](https://www.dropbox.com/s/9clhiihu8bcwxd1/dotfile-viewer-0.0.1.jar)*

`--dotfiles.folder` - specifies the path to the folder containing the dot files.
The default server port is 8080 but you can alter this with the `--server.port=<your port>` command property.

Open [http://localhost:8080](http://localhost:8080) in the browser: 
<img src="https://raw.githubusercontent.com/tzolov/dotfile-viewer/master/doc/Screen%20Shot%202015-01-07%20at%205.22.47%20PM.png" alt="alt text" width="400">

Use the filter control to filter in only the desired files.

An alternative (responsive) view of the files is available at [http://localhost:8080/responsive.html](http://localhost:8080/responsive.html):
<img src="https://raw.githubusercontent.com/tzolov/dotfile-viewer/master/doc/Screen%20Shot%202015-01-07%20at%205.23.39%20PM.png" alt="responsive view 1" width="200">
<img src="https://raw.githubusercontent.com/tzolov/dotfile-viewer/master/doc/Screen%20Shot%202015-01-07%20at%205.24.12%20PM.png" alt="responsive view 2" width="200">
<img src="https://raw.githubusercontent.com/tzolov/dotfile-viewer/master/doc/Screen%20Shot%202015-01-07%20at%205.24.25%20PM.png" alt="responsive view 3" width="200">




