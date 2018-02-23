# RPN Calculator

## Technical specs
- Java 8
- Maven 3

### Static Analysis
- PMD
- FindBug


## Building locally (you will need Java 8 and Maven)
./build.sh

## Building using Vagrant
```
- vagrant up (This will provision a Vagrant box with Java8 and Maven installed)
- vagrant ssh
- cd /vagrant
- ./build.sh
```

## Running RPN calculator App
`java -jar target/RPNCalculator-1.0.jar`


## Running using Docker Hub image
`docker run -it ramanjitsingh/rpndocker run -it ramanjitsingh/rpn`

## Author
Ramanjit Singh
