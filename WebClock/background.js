chrome.app.runtime.onLaunched.addListener(function() {
  chrome.app.window.create('test.html', {
    'outerBounds': {
      'width': 400,
      'height': 500
    },
    'alwaysOnTop': true
  });
});