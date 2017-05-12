import React from 'react';

import ReactDOM from 'react-dom';

import App from './App';

import injectTapEventPlugin from 'react-tap-event-plugin';

injectTapEventPlugin();

const root = document.getElementById('root');

ReactDOM.render(<App/>, root);
