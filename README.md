# dotfile-viewer
Self-contained solution to render Graphviz files on the web. A single jar that embeds Tomcat. 
Internally the viewer uses [Spring Boot](http://projects.spring.io/spring-boot/) and [Polymer](https://www.polymer-project.org/) and the custom [vis-js](http://tzolov.github.io/viz-js/components/viz-js) polymer-element.

[Demo 1](http://dotfile-viewer.cfapps.io/), [Demo 2](http://dotfile-viewer.cfapps.io/responsive.html)

## Usage

Build the project: 

```
    mvn clean install
```

and run the embedded Tomcat server with the dotfile viewer application:

```
    java -jar target/dotfile-viewer-0.0.2-SNAPSHOT.jar --dotfiles.pattern=<Ant like path pattern pointing to the dotfiles location>
```

*Also you can also download a pre-build  [dotfile-viewer-0.0.2.jar](https://www.dropbox.com/s/9clhiihu8bcwxd1/dotfile-viewer-0.0.2.jar)*

`--dotfiles.pattern` - ANT-style path to the location containing the dot files. 
Standardized prefixes are used to indicate the URL type of the source. This includes `file:` for accessing filesystem paths, `http:` for accessing resources via the HTTP protocol, `ftp:` for accessing resources via FTP, et. It also recognizes the special prefix `classpath:` for dotfiles which should be obtained from the classpath.

For example: `--dotfiles.pattern=file:/<path to the dotfiles folder>/**`. Note that you *must* set the `file:` prefix and the `**` wild-card. Additional examples and [pattern conventions](http://docs.spring.io/spring-framework/docs/2.5.x/api/org/springframework/core/io/support/PathMatchingResourcePatternResolver.html).

~~_Deprecated: `--dotfiles.folder` - folder path containing the dot files._~~  
 

Use the optional `--server.port=<your port>` parameter to change the default (8080) Tomcat server port.

Open [http://localhost:8080](http://localhost:8080) in the browser: 

<img src="https://raw.githubusercontent.com/tzolov/dotfile-viewer/master/doc/Screen%20Shot%202015-01-07%20at%205.22.47%20PM.png" alt="alt text" width="300">

Use the filter control to filter in only the desired files.

An alternative (responsive) view of the files is available at [http://localhost:8080/responsive.html](http://localhost:8080/responsive.html):

<img src="https://raw.githubusercontent.com/tzolov/dotfile-viewer/master/doc/Screen%20Shot%202015-01-07%20at%205.23.39%20PM.png" alt="responsive view 1" width="200">
<img src="https://raw.githubusercontent.com/tzolov/dotfile-viewer/master/doc/Screen%20Shot%202015-01-07%20at%205.24.25%20PM.png" alt="responsive view 3" width="130">




