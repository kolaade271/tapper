import { NativeModules } from 'react-native';
const { Tappa } = NativeModules;

export default {
  initializeTerminal: () => Tappa.initializeTerminal(),
  setErrorHandler: () => Tappa.setErrorHandler(),
  startTransaction: (amount, onSuccess, onError) =>
    Tappa.startTransaction(amount, onSuccess, onError),
};
