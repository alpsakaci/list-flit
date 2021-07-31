'use strict';

import React from 'react';
const client = require('../client');

class UserCard extends React.Component {

	constructor(props) {
		super(props);
		this.state = { username: String, profilePictureUrl: String };
	}

	componentDidMount() {
		client({ method: 'GET', path: '/me' }).done(response => {
			this.setState({ username: response.entity.displayName, profilePictureUrl: response.entity.images[0].url });
		});
	}

	render() {
		return (
			<div className="card my-2">
				<div className="row">
					<div className="col-2">
						<img
							className="rounded-circle mx-1 p-1"
							src={this.state.profilePictureUrl}
							width="100%"
						/>
					</div>
					<div className="col d-flex align-items-center">
						<div className="fw-lighter">
							Logged in as: {this.state.username} <br />
							<a href="/logout" >Logout</a>
						</div>
					</div>
				</div>
			</div >
		);
	}

}

export default UserCard;

