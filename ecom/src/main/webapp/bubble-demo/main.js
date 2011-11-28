/**
 * Register onload actions for jQuery here
 */
jQuery(document).ready( function() {
	showErrors();
    highlightErrors();
	roundBoxes();
    helpButtons();
    bindToggleXbar();
  });


function roundBoxes() {
  jQuery('div.rounded,div.rounded_all').corner('5px');
  jQuery('div.rounded-bottom,div.rounded_bottom').corner('5px bottom');
}

function helpButtons(id) {
    if (id) {
    	jQuery('#'+id).each(helpButton);
    } else {
    	jQuery('img.help_link').each(helpButton);
    }
}

function helpButton() {
	var self = jQuery(this);
    var helpText = self.attr('title');
    var helpBox = jQuery(document.createElement('DIV'));
    helpBox.append(
        jQuery('<div class="bubble help grey">')
          .append(
              jQuery('<img class="close" src="' + mardukConfig.contextPath + '/img/button_close_livehelp.gif"/>').click(function() {
                  jQuery(this).parent().hide();
              })
          ).append(helpText)
        );
    
    self.attr('title', '');
    self.click(function() {
        var msg = jQuery(this).parent().find('div > div.help:first');
        if (msg.css('display') == 'none') {
            msg.show();
            if (!msg.attr('bubbled')) {
                msg.attr('bubbled', true);
                msg.bubble().corner('5px');
            }
        } else {
            msg.hide();
        }
    });
    self.parent().prepend(helpBox);
}

function showErrors() {
	jQuery('div.bubble').fadeIn('slow').corner('5px round').bubble();
	jQuery('div.bubble-top').fadeIn('slow').corner('5px round').bubble('top');
}

function highlightErrors() {
	jQuery("div.error").each( function() {
		var name = jQuery(this).parent().attr("id");
		if (name) {
			jQuery("input[id='" + name + "'],select[id='" + name + "']").addClass("error");
		}
	});
	return true;
}

function cleanupErrors() {
	jQuery("div.error *").remove();
	jQuery(".error").removeClass("error");
	return true;
}

function bindToggleXbar() {
	jQuery("a.collapse").click(function() {
		jQuery("div.first_column,div.second_column").toggle();
		jQuery(this).toggleClass("collapse expand");
	});
}

// Busy ajax indicator stuff
function stopSearch() {
	document.getElementById('busy-ajax-indicator').style.display = 'none';
  	if (window.stop) {
    	window.stop();
  	} else if (document.execCommand) {
		document.execCommand('Stop');
  	}
  	return false;
}

function resizeBusyIndicator() {
  	var t = document.getElementById('lightbox'); 
  	t.style.height = (document.documentElement.scrollTop + document.body.offsetHeight) + 'px';
}

function movePopupMessage(id) {
	centerElement(id, 0, 0, 0);
}

function centerElement(divId, width, height, top) {
	var element = document.getElementById(divId);
	if (!element) {
		return;
	}
	var point = window.center({width:width,height:top});
	element.style.position = "absolute";
	element.style.top = point.y - 25 + "px";
	element.style.left = point.x - 125 + "px";
	if (width > 0) {
		element.style.width = width + "px";
	}
	if (height > 0) {
		element.style.height = height + "px";
	}
}

window.center = function() {
	var hWnd = (arguments[0] != null) ? arguments[0] : {width:0,height:0};

	var _x = 0;
	var _y = 0;
	var offsetX = 0;
	var offsetY = 0;

	//IE
	if(!window.pageYOffset) {
		//strict mode
		if(!(document.documentElement.scrollTop == 0)) {
			offsetY = document.documentElement.scrollTop;
			offsetX = document.documentElement.scrollLeft;
		}
		//quirks mode
		else {
			offsetY = document.body.scrollTop;
			offsetX = document.body.scrollLeft;
		}
	}
	//w3c
	else {
		offsetX = window.pageXOffset;
		offsetY = window.pageYOffset;
	}

	_x = ((this.size().width-hWnd.width)/2)+offsetX;
	_y = ((this.size().height-hWnd.height)/2)+offsetY;

	return{x:_x,y:_y};
}

window.size = function() {
	var w = 0;
	var h = 0;

	//IE
	if(!window.innerWidth) {
		//strict mode
		if(!(document.documentElement.clientWidth == 0)) {
			w = document.documentElement.clientWidth;
			h = document.documentElement.clientHeight;
		}
		//quirks mode
		else {
			w = document.body.clientWidth;
			h = document.body.clientHeight;
		}
	}
	//w3c
	else {
		w = window.innerWidth;
		h = window.innerHeight;
	}
	return {width:w,height:h};
}
//END Busy ajax indicator stuff