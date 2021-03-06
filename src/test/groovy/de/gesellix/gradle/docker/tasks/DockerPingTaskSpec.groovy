package de.gesellix.gradle.docker.tasks

import de.gesellix.docker.client.DockerClient
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class DockerPingTaskSpec extends Specification {

    def project
    def task
    def dockerClient = Mock(DockerClient)

    def setup() {
        project = ProjectBuilder.builder().build()
        task = project.task('dockerPing', type: DockerPingTask)
        task.dockerClient = dockerClient
    }

    def "delegates to dockerClient and saves result"() {
        when:
        task.execute()

        then:
        1 * dockerClient.ping() >> ["some result"]

        and:
        task.result == ["some result"]
    }
}
