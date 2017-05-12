/**
 * Created by wallance on 3/13/17.
 */
import { createStore, applyMiddleware, compose } from 'redux';
import middleware from '../middleware';
import rootReducer from '../reducers';

let finalCreateStore = compose(
    applyMiddleware.apply(this, middleware)(createStore)
);

export const store = finalCreateStore(rootReducer);
