class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "Index", action: "index")
        "/index.html"(controller: "Index", action: "index")
        "/index.htm"(controller: "Index", action: "index")

        "/about"(controller: "About", action: "index")
        "/config"(controller: "Config", action: "index")
        "/sendmessage"(controller: "Telegram", action: "sendMessage")
        "/honeypot"(controller: "Honeypot", action: "index")

        "/saveConfig"(controller: "Config", action: "saveConfig")


        //
        // honeypot calls
        //

        "/updateip"(controller: "Honeypot", action: "updateIP")
        "/reportalarm"(controller: "Honeypot", action: "report")

        "/Report/confirm/$id"(controller: "Report", action: "moveToAlarm")

        "/graph"(controller: "Graph", action:"index")

        "/tweet"(controller: "Twitter", action: "index")


	}
}
