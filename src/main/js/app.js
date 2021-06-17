'use strict';

import React from 'react';
import UserCard from './components/UserCard';
import AppleLibraryUploadForm from './components/AppleLibraryUploadForm';
import ListTracks from './components/ListTracks';
const ReactDOM = require('react-dom');


class App extends React.Component {

	render() {
		return (
			<div>
				<UserCard />
				<AppleLibraryUploadForm />
				<ListTracks />
			</div>
		)
	}

}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
