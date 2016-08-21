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
        "/honeypot/delete/$id"(controller: "Honeypot", action: "delete")
        "/saveHoneypot"(controller: "Honeypot", action: "saveHoneypot")

        "/saveConfig"(controller: "Config", action: "saveConfig")
        "/login_"(controller: "LoginFake", action: "index")

        "/graph"(controller: "Graph", action:"index")

        "/tweet"(controller: "Twitter", action: "index")
        "/User"(controller: "User", action: "index")
        "/addUser"(controller: "User", action: "add")
        "/delUser"(controller: "User", action: "del")
        "/newUser"(controller: "User", action: "newUser")



        //
        // honeypot calls
        //

        "/updateip"(controller: "Honeypot", action: "updateIP")
        "/reportalarm"(controller: "Honeypot", action: "report")
        "/ews-0.1/alert/postSimpleMessage"(controller: "Honeypot", action: "report")

        "/Report/confirm/$id"(controller: "Report", action: "moveToAlarm")
        "/Report/delete/$id"(controller: "Report", action: "remove")
        "/Report/ignore/$id"(controller: "Report", action: "ignore")
        "/Report/show/$id"(controller: "HandleReports", action: "show")




	}
}
