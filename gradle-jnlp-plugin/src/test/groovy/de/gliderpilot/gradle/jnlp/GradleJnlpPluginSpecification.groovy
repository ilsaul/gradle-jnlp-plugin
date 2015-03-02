/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.gliderpilot.gradle.jnlp

class GradleJnlpPluginSpecification extends AbstractPluginSpecification {

    def setupSpec() {
        project {
            apply plugin: 'de.gliderpilot.jnlp'
        }
    }

    def "plugin was applied"() {
        expect:
        project.plugins.hasPlugin('de.gliderpilot.jnlp')

        and:
        project.plugins['de.gliderpilot.jnlp'].class == GradleJnlpPlugin
    }

    def "when distribution plugin is applied, jnlp plugin automatically creates a distribution"() {
        when:
        project.apply plugin: 'distribution'

        then:
        project.distributions.names.contains 'webstart'

        and:
        project.distributions['webstart'].contents.sourcePaths.contains(project.tasks.createWebstartDir)
    }

}
