/*
 * SonarQube
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarsource.sonarqube.upgrade;

import com.sonar.orchestrator.Orchestrator;
import com.sonar.orchestrator.OrchestratorBuilder;
import org.junit.Test;

public class IpConflictTest {
  @Test
  public void reproduce_port_conflict() {
    for (int i = 0; i < 5_000; i++) {
      System.out.println("------------- run " + i);
      launch();
    }
  }

  private void launch() {
    OrchestratorBuilder builder = Orchestrator.builderEnv()
      .setSonarVersion("DEV")
      .setStartupLogWatcher(log -> log.contains("Process[web] is up"));
    Orchestrator orchestrator = builder.build();
    try {
      orchestrator.start();
    } finally {
      orchestrator.stop();
    }
  }
}
