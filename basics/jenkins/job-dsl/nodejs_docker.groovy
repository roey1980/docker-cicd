job('NodeJS Docker example') {
    scm {
        git('git://github.com/roey1980/docker-cicd.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@ndomain.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
//     wrappers {
//         nodejs('nodejs-new') 
//     }
    steps {
        dockerBuildAndPublish {
            repositoryName('roeyydocker/nodejsdocker')
            buildContext('basics/jenkins/job-dsl/nodejs_docker.groovy')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
