'use strict';

import React from 'react';

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
			<div className="card my-2 fw-lighter">
				<div className="card-body">
					<form onSubmit={this.handleSubmit} >
						<div className="mb-3">
							<label className="form-label">Apple Music Library File</label>
							<input type="file" className="form-control" value={this.state.value} onChange={this.handleChange} required />
						</div>
						<input type="submit" className="btn btn-primary w-100" value="Upload" />
					</form>
				</div>
			</div >
		);
	}

}

export default AppleLibraryUploadForm;
