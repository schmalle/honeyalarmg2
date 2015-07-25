
<html>
<head>
    <link rel="stylesheet" href="http://cdn.graphalchemist.com/alchemy.min.css"/>
</head>
<body>
<div class="alchemy" id="alchemy"></div>

<script src="http://cdn.graphalchemist.com/alchemy.min.js"></script>
<script type="text/javascript">
    var config = {
        dataSource: "/json/irc.json",
        edgeTypes: {"edgeType":["IS_IN", "REFERENCED"]},
        nodeTypes: {"nodeType":["room", "user"]},
        forceLocked: false,
        nodeCaption: 'id',
        edgeCaption: 'edgeType',
        nodeStyle: {
            "room": {
                "color"      : "#F6F",
                "radius"     : 20,
                "borderWidth": 8
            },
            "user":{
                "radius"     : 4
            }
        },
        edgeStyle: {
            "IS_IN": {
                "width": 1,
                "color": "rgba(255,255,255,0.4)"
            },
            "REFERENCED": {
                "width": 3,
                "color": "rgb(0,255,0)"
            }
        }
    }

    alchemy = new Alchemy(config)
</script>
</body>
</html>