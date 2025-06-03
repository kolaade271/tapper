const { NativeModules } = require('react-native');
const { Tappa } = NativeModules;

module.exports = {
  initializeTerminal: () => Tappa.initializeTerminal(),
  setErrorHandler: () => Tappa.setErrorHandler(),
  startTransaction: (amount, onSuccess, onError) =>
    Tappa.startTransaction(amount, onSuccess, onError),
};
