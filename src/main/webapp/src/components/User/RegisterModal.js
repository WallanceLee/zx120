/**
 * Created by wallance on 2/27/17.
 */
/**
 * Created by wallance on 2/26/17.
 */
import React, { Component } from 'react';

import Modal from 'react-bootstrap/lib/Modal';
import Button from 'react-bootstrap/lib/Button';

class RegisterModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: true
        };
    }

    close() {
        this.setState({
            showModal: false
        });
    }

    submit() {

    }

    render() {
        return (
            <Modal show={this.state.showModal} onHide={this.close.bind(this)}>
                <Modal.Header closeButton>
                    <Modal.Title>注册新用户</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form className="form-signin">
                        <label className="sr-only">用户名</label>
                        <input type="text"
                               id="username" name="username"
                               className="form-control"
                               placeholder="请输入用户名"
                               required={true}
                               autofocus={true}/>
                        <label className="sr-only">密码</label>
                        <input type="password"
                               id="password" name="password"
                               className="form-control"
                               placeholder="请输入密码"
                               required={true}
                               autofocus={true}/>
                        <label className="sr-only">密码确认</label>
                        <input type="password"
                               id="password-confirm" name="password-confirm"
                               className="form-control"
                               placeholder="请再次输入密码"
                               required={true}
                               autofocus={true}/>
                    </form>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={this.close.bind(this)}>取消</Button>
                    <Button onClick={this.submit.bind(this)}>提交</Button>
                </Modal.Footer>
            </Modal>
        );
    }
}

export default RegisterModal;
