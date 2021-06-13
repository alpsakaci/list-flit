'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class UserCard extends React.Component {

	render() {
		return (
			<div class="card my-4">
				<div class="row">
					<div class="col-2">
						<img
							class="rounded-circle mx-1 p-1"
							src={this.props.url}
							width="100%"
						/>
					</div>
					<div class="col d-flex align-items-center">
						<div class="fw-lighter">
							Logged in as: {this.props.username} <br />
							<a href="/logout" >Logout</a>
						</div>

					</div>
				</div>
			</div >
		);
	}

}

class AppleLibraryUploadForm extends React.Component {

	constructor(props) {
		super(props);
		this.state = { file: '' };
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleChange(event) {
		this.setState({ file: event.target.files[0] });
	}

	handleSubmit(event) {
		const formData = new FormData();
		formData.append('libraryFile', this.state.file)

		let res = fetch('/library', {
			mode: 'no-cors',
			method: 'POST',
			body: formData,
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'multipart/form-data; boundary=<calculated when request is sent>'
			}
		}).then(response => {
			//
		});


		event.preventDefault();
	}

	render() {
		return (
			<div class="card fw-lighter">
				<div class="card-body">
					<form onSubmit={this.handleSubmit} >
						<div class="mb-3">
							<label class="form-label">Apple Music Library File</label>
							<input type="file" class="form-control" value={this.state.value} onChange={this.handleChange} required />
						</div>
						<input type="submit" class="btn btn-primary w-100" value="Upload" />
					</form>
				</div>
			</div >
		);
	}

}

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = { username: String, profilePicture: String };
	}

	componentDidMount() {
		client({ method: 'GET', path: '/me' }).done(response => {
			this.setState({ username: response.entity.displayName, profilePicture: response.entity.images[0].url });
		});
	}

	render() {
		return (
			<div>
				<UserCard username={this.state.username} url={this.state.profilePicture} />
				<AppleLibraryUploadForm />
			</div>
		)
	}

}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
