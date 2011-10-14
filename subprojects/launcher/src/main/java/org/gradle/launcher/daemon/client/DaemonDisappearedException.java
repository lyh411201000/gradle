/*
 * Copyright 2011 the original author or authors.
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
package org.gradle.launcher.daemon.client;

import org.gradle.api.GradleException;
import org.gradle.launcher.daemon.protocol.Command;
import org.gradle.messaging.remote.internal.Connection;

/**
 * Thrown when a daemon disconnects unexpectedly while a client is interacting with it.
 */
public class DaemonDisappearedException extends GradleException {

    public static final String MESSAGE = "Gradle build daemon disappeared unexpectedly (it may have been stopped, killed or may have crashed)";

    private final Command lastSent;
    private final Connection<?> connection;

    public DaemonDisappearedException(Command lastSent, Connection<?> connection) {
        super(MESSAGE);
        this.lastSent = lastSent;
        this.connection = connection;
    }

}