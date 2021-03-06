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

package org.gradle.initialization

import org.gradle.StartParameter
import org.gradle.api.internal.GradleInternal
import org.gradle.groovy.scripts.ScriptSource

/**
 * @author Hans Dockter
 */
public class DefaultSettings extends BaseSettings {
    public DefaultSettings() {}

    DefaultSettings(GradleInternal gradle,
                    IProjectDescriptorRegistry projectDescriptorRegistry,
                    URLClassLoader classloader, File settingsDir,
                    ScriptSource settingsScript, StartParameter startParameter) {
      super(gradle, projectDescriptorRegistry, classloader, settingsDir, settingsScript, startParameter)
    }

    def propertyMissing(String property) {
        return dynamicObject.getProperty(property)
    }

    void setProperty(String name, value) {
        dynamicObject.setProperty(name, value)
    }
}
