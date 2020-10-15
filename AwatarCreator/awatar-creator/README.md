# awatar-creator
This application is a prototype to create test cases and scenarios with according the Behavior test driven approach. 
This application belongs to the master thesis projekt of '' from Alexander Hoedl at FH Campus02.

This application is based on vueJS and required the module 'AwatarCore' to load data from backend. It is the graphical 
user interface to write test cases in human readable form.

## Project setup

### Requirements
- Nodejs (https://nodejs.org/en/download/)
- NPM (https://www.npmjs.com/get-npm)
- vueJs (https://cli.vuejs.org/guide/installation.html)
- Awatar-Core (https://github.com/hoedlale16/AWATAR)

After installation of all required applications execute to load and install all required resources: 
```
npm install
```

### Compiles and hot-reloads for development
You can start the development server by executing:
```
npm run serve
```
The application requires a connection to 'AwatarCore'. Therefore configure a correct IP of Server in proxy property in file 'vue.config.js'
Usually the Webapplication starts at URL ``http://localhost:8081/``. 

### Compiles and minifies for production
```
npm run build
```

Compiles JS-Files and store in dist directory to append in our productive environment.

