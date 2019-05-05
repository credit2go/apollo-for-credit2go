#!/usr/bin/env groovy
// This file is used to add additonal function using in default global library
// This file is available only after checkout scm is done

//代码更新后的额外步骤
def postCheckout(stepTimeout) {
    //main function here
}

//编译前
def preCompile(stepTimeout) {
    //main function here
}

//编译后
def postCompile(stepTimeout) {
    //main function here
    stage('发布自定义apollo-client') {
        container('maven') {
            String version = readMavenPom(file: 'pom.xml').getVersion()
            dir('apollo-client-credit2go') {
                //replace version
                sh 'sed -i \'s/${apollo.client.version}/' + version + '/g\' pom.xml'
                String commandDeploy = 'mvn -B -V -U -e -amd clean deploy ' + '-Dapollo.client.version=' + version
                sh commandDeploy
                archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/*.jar', fingerprint: true, onlyIfSuccessful: true
            }
        }
    }
}

//打包前
def prePackage(stepTimeout) {
    //main function here
}

//打包后
def postPackage(stepTimeout) {
    //main function here
}

//项目构建后
def postBuild(stepTimeout) {
    //main function here
}

//Alway keep this line to avoid null object
return this