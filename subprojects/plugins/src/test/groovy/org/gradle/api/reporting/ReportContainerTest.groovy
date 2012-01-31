/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



package org.gradle.api.reporting

import org.gradle.api.InvalidUserDataException
import org.gradle.api.internal.AsmBackedClassGenerator
import org.gradle.api.internal.ClassGeneratorBackedInstantiator
import org.gradle.api.internal.DirectInstantiator
import org.gradle.api.internal.Instantiator
import org.gradle.api.internal.file.IdentityFileResolver
import org.gradle.api.reporting.internal.SimpleReport
import spock.lang.Specification

class ReportContainerTest extends Specification {

    Instantiator instantiator = new ClassGeneratorBackedInstantiator(new AsmBackedClassGenerator(), new DirectInstantiator());


    Report createReport(String name) {
        instantiator.newInstance(SimpleReport, name, new IdentityFileResolver())
    }

    ReportContainer createContainer(Report... reports) {
        instantiator.newInstance(ReportContainer, reports.toList())
    }

    def container

    def setup() {
        container = createContainer(createReport("a"), createReport("b"), createReport("c"))
    }
    
    def "reports given at construction are available"() {
        when:
        container.configure { a { } }

        then:
        notThrown(MissingPropertyException)
    }

    def "container is immutable"() {
        when:
        container.add(createReport("d"))
        
        then:
        thrown(ReportContainer.ImmutableViolationException)
        
        when:
        container.clear()

        then:
        thrown(ReportContainer.ImmutableViolationException)
    }
    
    def "enable empty by default"() {
        expect:
        container.every { !it.enabled } && container.enabled.empty
    }
    
    def "can change enabled"() {
        when:
        container.each { it.enabled = false }
        
        then:
        container.enabled.empty
        
        when:
        container.configure {
            a.enabled true
            b.enabled true
        }
        
        then:
        container.enabled.size() == 2
    }

    def "cannot add report named 'enabled'"() {
        when:
        createContainer(createReport("enabled"))
        
        then:
        thrown(InvalidUserDataException)
    }
}