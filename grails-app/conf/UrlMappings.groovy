class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/details"(controller: "Details", action: "index")
        "/"(view:"/index")
        "/ng2"(controller: "Indexng", action: "index")
        "/updateip"(controller: "Honeypot", action: "updateIP")

        "500"(view:'/error')
	}
}
