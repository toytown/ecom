/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ecom.web.utils;

import java.io.Serializable;

/**
 * Checks for bots from User-Agent header
 * 
 * @author vytautas r.
 */
public class BotAgent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] BOT_AGENTS = { "googlebot", "msnbot", "slurp", "jeeves", "appie", "architext", "jeeves", "bjaaland", "ferret",
			"gulliver", "harvest", "htdig", "linkwalker", "lycos_", "moget", "muscatferret", "myweb", "nomad", "scooter", "yahoo!\\sslurp\\schina",
			"slurp", "weblayers", "antibot", "bruinbot", "digout4u", "echo!", "ia_archiver", "jennybot", "mercator", "netcraft", "msnbot",
			"petersnews", "unlost_web_crawler", "voila", "webbase", "webcollage", "cfetch", "zyborg", "wisenutbot", "robot", "crawl", "spider" };


	/**
	 * @param agent
	 *            agent to verify
	 * @return true if User-Agent seems to be a bot
	 */
	public static boolean isAgent(final String agent) {
		if (agent != null) {
			final String lowerAgent = agent.toLowerCase();
			for (final String bot : BOT_AGENTS) {
				if (lowerAgent.indexOf(bot) != -1) {
					return true;
				}
			}
		}
		return false;
	}

}
