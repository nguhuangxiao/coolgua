webpackJsonp([5],{

/***/ 1219:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _upload = __webpack_require__(517);

var _upload2 = _interopRequireDefault(_upload);

var _button = __webpack_require__(57);

var _button2 = _interopRequireDefault(_button);

var _icon = __webpack_require__(16);

var _icon2 = _interopRequireDefault(_icon);

var _spin = __webpack_require__(105);

var _spin2 = _interopRequireDefault(_spin);

var _message2 = __webpack_require__(212);

var _message3 = _interopRequireDefault(_message2);

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

__webpack_require__(1316);

__webpack_require__(510);

__webpack_require__(515);

__webpack_require__(513);

__webpack_require__(512);

var _react = __webpack_require__(0);

var _react2 = _interopRequireDefault(_react);

var _antd = __webpack_require__(210);

var _sidebar = __webpack_require__(1291);

var _sidebar2 = _interopRequireDefault(_sidebar);

var _breadnav = __webpack_require__(1271);

var _breadnav2 = _interopRequireDefault(_breadnav);

var _util = __webpack_require__(104);

var _activity_add = __webpack_require__(1317);

var _canvas = __webpack_require__(1511);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var orgId = (0, _util.getLocalStorage)('orgId');
var fp = (0, _util.getLocalStorage)('fp');
var That = void 0;
var eventId = void 0;
var props = {
  name: 'file',
  action: "/event/uploadBanner/" + orgId + "/" + fp,
  data: { "eventId": eventId },
  listType: 'picture',
  className: 'upload-list-inline',
  headers: {
    authorization: 'authorization-text'
  },
  onChange: function onChange(info) {
    var isJPG = info.file.type === 'image/jpeg' || info.file.type === 'image/png';
    if (!isJPG) {
      return;
    }
    var isLt2M = info.file.size / 1024 / 1024 < 2;
    if (!isLt2M) {
      return;
    }
    if (info.file.status !== 'uploading') {
      That.setState({
        logo: info.file.response.map.path,
        fileList: info.fileList
      });
    }
    if (info.file.status === 'done') {
      _message3.default.success(info.file.name + ' \u6587\u4EF6\u4E0A\u4F20\u6210\u529F');
    } else if (info.file.status === 'error') {
      _message3.default.error(info.file.name + ' \u6587\u4EF6\u4E0A\u4F20\u5931\u8D25');
    }
  }
};

function beforeUpload(file) {
  var isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
  if (!isJPG) {
    _message3.default.error('请上传图片类型!');
  }
  var isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {
    _message3.default.error('图片大小不能超过2MB!');
  }
  return isJPG && isLt2M;
}

var Entrance = function (_React$Component) {
  _inherits(Entrance, _React$Component);

  function Entrance(props, context) {
    _classCallCheck(this, Entrance);

    var _this = _possibleConstructorReturn(this, (Entrance.__proto__ || Object.getPrototypeOf(Entrance)).call(this, props, context));

    _this.state = {
      loading: false,
      logo: "",
      acode: "",
      qrcode: "",
      fileList: []
    };
    return _this;
  }

  _createClass(Entrance, [{
    key: 'saveLocalLogo',
    value: function saveLocalLogo(name) {
      (0, _canvas.saveWXLogo)(name);
    }
  }, {
    key: 'render',
    value: function render() {
      return _react2.default.createElement(
        'div',
        { className: 'wrapper' },
        _react2.default.createElement(
          'div',
          { className: 'containner' },
          _react2.default.createElement(_breadnav2.default, null)
        ),
        _react2.default.createElement(
          'div',
          { className: 'containner main-flex' },
          _react2.default.createElement(_sidebar2.default, null),
          this.state.acode === "" ? _react2.default.createElement(
            'div',
            { className: 'loading' },
            _react2.default.createElement(_spin2.default, { size: 'large' }),
            '\u6570\u636E\u52A0\u8F7D\u4E2D...'
          ) : _react2.default.createElement(
            'div',
            { className: 'side-main entrance-main offsetLeft' },
            _react2.default.createElement(
              'div',
              { className: 'card-trance' },
              _react2.default.createElement(
                'div',
                { className: 'card-list' },
                _react2.default.createElement(
                  'div',
                  { className: 'box pic1', id: 'wxlogo1' },
                  _react2.default.createElement('img', { src: this.state.acode, className: 'qcode' }),
                  this.state.logo ? _react2.default.createElement('img', { src: this.state.logo, className: 'logo' }) : ""
                ),
                _react2.default.createElement(
                  'p',
                  null,
                  _react2.default.createElement(
                    'button',
                    { className: 'btn', onClick: this.saveLocalLogo.bind(this, 'wxlogo1') },
                    '\u4E0B\u8F7D'
                  )
                )
              ),
              _react2.default.createElement(
                'div',
                { className: 'card-list' },
                _react2.default.createElement(
                  'div',
                  { className: 'box pic2', id: 'wxlogo2' },
                  _react2.default.createElement('img', { src: this.state.qrcode, className: 'qcode' }),
                  this.state.logo ? _react2.default.createElement('img', { src: this.state.logo, className: 'logo' }) : ""
                ),
                _react2.default.createElement(
                  'p',
                  { style: { marginTop: 50 } },
                  _react2.default.createElement(
                    'button',
                    { className: 'btn', onClick: this.saveLocalLogo.bind(this, 'wxlogo2') },
                    '\u4E0B\u8F7D'
                  )
                )
              )
            ),
            _react2.default.createElement(
              'div',
              { className: 'previous-box' },
              this.state.logo ? _react2.default.createElement(
                'div',
                { className: 'previous-picture' },
                _react2.default.createElement('img', { src: this.state.logo, className: 'logo' })
              ) : "",
              _react2.default.createElement(
                'div',
                { className: 'info' },
                _react2.default.createElement(
                  'div',
                  { className: 'upload-logo' },
                  _react2.default.createElement(
                    _upload2.default,
                    _extends({}, props, {
                      beforeUpload: beforeUpload,
                      showUploadList: false
                    }),
                    _react2.default.createElement(
                      _button2.default,
                      { className: 'upload-btn' },
                      _react2.default.createElement(_icon2.default, { type: 'upload' }),
                      '\u4E0A\u4F20Logo'
                    )
                  )
                ),
                _react2.default.createElement(
                  'span',
                  null,
                  '\u6CE8\uFF1Alogo\u56FE\u7247\u5C3A\u5BF8\u4E3A\uFF1A68*68'
                )
              )
            )
          )
        ),
        _react2.default.createElement('canvas', { className: 'pic1-canvas', id: 'wxlogo1-canvas' }),
        _react2.default.createElement('canvas', { className: 'pic2-canvas', id: 'wxlogo2-canvas' })
      );
    }
  }, {
    key: 'componentDidMount',
    value: function componentDidMount() {
      That = this;
      eventId = this.props.match.params.id;
      props.data = { "eventId": eventId };

      var listItem = (0, _activity_add.getActivityById)(eventId);

      listItem.then(function (res) {
        if (res.code === 200) {
          That.setState({
            acode: res.event.acode,
            qrcode: res.event.qrcode,
            logo: res.event.logo
          });
        }
      });
    }
  }]);

  return Entrance;
}(_react2.default.Component);

exports.default = Entrance;

/***/ }),

/***/ 1244:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var replace = String.prototype.replace;
var percentTwenties = /%20/g;

module.exports = {
    'default': 'RFC3986',
    formatters: {
        RFC1738: function (value) {
            return replace.call(value, percentTwenties, '+');
        },
        RFC3986: function (value) {
            return value;
        }
    },
    RFC1738: 'RFC1738',
    RFC3986: 'RFC3986'
};


/***/ }),

/***/ 1245:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var has = Object.prototype.hasOwnProperty;

var hexTable = (function () {
    var array = [];
    for (var i = 0; i < 256; ++i) {
        array.push('%' + ((i < 16 ? '0' : '') + i.toString(16)).toUpperCase());
    }

    return array;
}());

var compactQueue = function compactQueue(queue) {
    var obj;

    while (queue.length) {
        var item = queue.pop();
        obj = item.obj[item.prop];

        if (Array.isArray(obj)) {
            var compacted = [];

            for (var j = 0; j < obj.length; ++j) {
                if (typeof obj[j] !== 'undefined') {
                    compacted.push(obj[j]);
                }
            }

            item.obj[item.prop] = compacted;
        }
    }

    return obj;
};

exports.arrayToObject = function arrayToObject(source, options) {
    var obj = options && options.plainObjects ? Object.create(null) : {};
    for (var i = 0; i < source.length; ++i) {
        if (typeof source[i] !== 'undefined') {
            obj[i] = source[i];
        }
    }

    return obj;
};

exports.merge = function merge(target, source, options) {
    if (!source) {
        return target;
    }

    if (typeof source !== 'object') {
        if (Array.isArray(target)) {
            target.push(source);
        } else if (typeof target === 'object') {
            if (options.plainObjects || options.allowPrototypes || !has.call(Object.prototype, source)) {
                target[source] = true;
            }
        } else {
            return [target, source];
        }

        return target;
    }

    if (typeof target !== 'object') {
        return [target].concat(source);
    }

    var mergeTarget = target;
    if (Array.isArray(target) && !Array.isArray(source)) {
        mergeTarget = exports.arrayToObject(target, options);
    }

    if (Array.isArray(target) && Array.isArray(source)) {
        source.forEach(function (item, i) {
            if (has.call(target, i)) {
                if (target[i] && typeof target[i] === 'object') {
                    target[i] = exports.merge(target[i], item, options);
                } else {
                    target.push(item);
                }
            } else {
                target[i] = item;
            }
        });
        return target;
    }

    return Object.keys(source).reduce(function (acc, key) {
        var value = source[key];

        if (has.call(acc, key)) {
            acc[key] = exports.merge(acc[key], value, options);
        } else {
            acc[key] = value;
        }
        return acc;
    }, mergeTarget);
};

exports.assign = function assignSingleSource(target, source) {
    return Object.keys(source).reduce(function (acc, key) {
        acc[key] = source[key];
        return acc;
    }, target);
};

exports.decode = function (str) {
    try {
        return decodeURIComponent(str.replace(/\+/g, ' '));
    } catch (e) {
        return str;
    }
};

exports.encode = function encode(str) {
    // This code was originally written by Brian White (mscdex) for the io.js core querystring library.
    // It has been adapted here for stricter adherence to RFC 3986
    if (str.length === 0) {
        return str;
    }

    var string = typeof str === 'string' ? str : String(str);

    var out = '';
    for (var i = 0; i < string.length; ++i) {
        var c = string.charCodeAt(i);

        if (
            c === 0x2D // -
            || c === 0x2E // .
            || c === 0x5F // _
            || c === 0x7E // ~
            || (c >= 0x30 && c <= 0x39) // 0-9
            || (c >= 0x41 && c <= 0x5A) // a-z
            || (c >= 0x61 && c <= 0x7A) // A-Z
        ) {
            out += string.charAt(i);
            continue;
        }

        if (c < 0x80) {
            out = out + hexTable[c];
            continue;
        }

        if (c < 0x800) {
            out = out + (hexTable[0xC0 | (c >> 6)] + hexTable[0x80 | (c & 0x3F)]);
            continue;
        }

        if (c < 0xD800 || c >= 0xE000) {
            out = out + (hexTable[0xE0 | (c >> 12)] + hexTable[0x80 | ((c >> 6) & 0x3F)] + hexTable[0x80 | (c & 0x3F)]);
            continue;
        }

        i += 1;
        c = 0x10000 + (((c & 0x3FF) << 10) | (string.charCodeAt(i) & 0x3FF));
        out += hexTable[0xF0 | (c >> 18)]
            + hexTable[0x80 | ((c >> 12) & 0x3F)]
            + hexTable[0x80 | ((c >> 6) & 0x3F)]
            + hexTable[0x80 | (c & 0x3F)];
    }

    return out;
};

exports.compact = function compact(value) {
    var queue = [{ obj: { o: value }, prop: 'o' }];
    var refs = [];

    for (var i = 0; i < queue.length; ++i) {
        var item = queue[i];
        var obj = item.obj[item.prop];

        var keys = Object.keys(obj);
        for (var j = 0; j < keys.length; ++j) {
            var key = keys[j];
            var val = obj[key];
            if (typeof val === 'object' && val !== null && refs.indexOf(val) === -1) {
                queue.push({ obj: obj, prop: key });
                refs.push(val);
            }
        }
    }

    return compactQueue(queue);
};

exports.isRegExp = function isRegExp(obj) {
    return Object.prototype.toString.call(obj) === '[object RegExp]';
};

exports.isBuffer = function isBuffer(obj) {
    if (obj === null || typeof obj === 'undefined') {
        return false;
    }

    return !!(obj.constructor && obj.constructor.isBuffer && obj.constructor.isBuffer(obj));
};


/***/ }),

/***/ 1258:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var stringify = __webpack_require__(1263);
var parse = __webpack_require__(1262);
var formats = __webpack_require__(1244);

module.exports = {
    formats: formats,
    parse: parse,
    stringify: stringify
};


/***/ }),

/***/ 1262:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var utils = __webpack_require__(1245);

var has = Object.prototype.hasOwnProperty;

var defaults = {
    allowDots: false,
    allowPrototypes: false,
    arrayLimit: 20,
    decoder: utils.decode,
    delimiter: '&',
    depth: 5,
    parameterLimit: 1000,
    plainObjects: false,
    strictNullHandling: false
};

var parseValues = function parseQueryStringValues(str, options) {
    var obj = {};
    var cleanStr = options.ignoreQueryPrefix ? str.replace(/^\?/, '') : str;
    var limit = options.parameterLimit === Infinity ? undefined : options.parameterLimit;
    var parts = cleanStr.split(options.delimiter, limit);

    for (var i = 0; i < parts.length; ++i) {
        var part = parts[i];

        var bracketEqualsPos = part.indexOf(']=');
        var pos = bracketEqualsPos === -1 ? part.indexOf('=') : bracketEqualsPos + 1;

        var key, val;
        if (pos === -1) {
            key = options.decoder(part, defaults.decoder);
            val = options.strictNullHandling ? null : '';
        } else {
            key = options.decoder(part.slice(0, pos), defaults.decoder);
            val = options.decoder(part.slice(pos + 1), defaults.decoder);
        }
        if (has.call(obj, key)) {
            obj[key] = [].concat(obj[key]).concat(val);
        } else {
            obj[key] = val;
        }
    }

    return obj;
};

var parseObject = function (chain, val, options) {
    var leaf = val;

    for (var i = chain.length - 1; i >= 0; --i) {
        var obj;
        var root = chain[i];

        if (root === '[]') {
            obj = [];
            obj = obj.concat(leaf);
        } else {
            obj = options.plainObjects ? Object.create(null) : {};
            var cleanRoot = root.charAt(0) === '[' && root.charAt(root.length - 1) === ']' ? root.slice(1, -1) : root;
            var index = parseInt(cleanRoot, 10);
            if (
                !isNaN(index)
                && root !== cleanRoot
                && String(index) === cleanRoot
                && index >= 0
                && (options.parseArrays && index <= options.arrayLimit)
            ) {
                obj = [];
                obj[index] = leaf;
            } else {
                obj[cleanRoot] = leaf;
            }
        }

        leaf = obj;
    }

    return leaf;
};

var parseKeys = function parseQueryStringKeys(givenKey, val, options) {
    if (!givenKey) {
        return;
    }

    // Transform dot notation to bracket notation
    var key = options.allowDots ? givenKey.replace(/\.([^.[]+)/g, '[$1]') : givenKey;

    // The regex chunks

    var brackets = /(\[[^[\]]*])/;
    var child = /(\[[^[\]]*])/g;

    // Get the parent

    var segment = brackets.exec(key);
    var parent = segment ? key.slice(0, segment.index) : key;

    // Stash the parent if it exists

    var keys = [];
    if (parent) {
        // If we aren't using plain objects, optionally prefix keys
        // that would overwrite object prototype properties
        if (!options.plainObjects && has.call(Object.prototype, parent)) {
            if (!options.allowPrototypes) {
                return;
            }
        }

        keys.push(parent);
    }

    // Loop through children appending to the array until we hit depth

    var i = 0;
    while ((segment = child.exec(key)) !== null && i < options.depth) {
        i += 1;
        if (!options.plainObjects && has.call(Object.prototype, segment[1].slice(1, -1))) {
            if (!options.allowPrototypes) {
                return;
            }
        }
        keys.push(segment[1]);
    }

    // If there's a remainder, just add whatever is left

    if (segment) {
        keys.push('[' + key.slice(segment.index) + ']');
    }

    return parseObject(keys, val, options);
};

module.exports = function (str, opts) {
    var options = opts ? utils.assign({}, opts) : {};

    if (options.decoder !== null && options.decoder !== undefined && typeof options.decoder !== 'function') {
        throw new TypeError('Decoder has to be a function.');
    }

    options.ignoreQueryPrefix = options.ignoreQueryPrefix === true;
    options.delimiter = typeof options.delimiter === 'string' || utils.isRegExp(options.delimiter) ? options.delimiter : defaults.delimiter;
    options.depth = typeof options.depth === 'number' ? options.depth : defaults.depth;
    options.arrayLimit = typeof options.arrayLimit === 'number' ? options.arrayLimit : defaults.arrayLimit;
    options.parseArrays = options.parseArrays !== false;
    options.decoder = typeof options.decoder === 'function' ? options.decoder : defaults.decoder;
    options.allowDots = typeof options.allowDots === 'boolean' ? options.allowDots : defaults.allowDots;
    options.plainObjects = typeof options.plainObjects === 'boolean' ? options.plainObjects : defaults.plainObjects;
    options.allowPrototypes = typeof options.allowPrototypes === 'boolean' ? options.allowPrototypes : defaults.allowPrototypes;
    options.parameterLimit = typeof options.parameterLimit === 'number' ? options.parameterLimit : defaults.parameterLimit;
    options.strictNullHandling = typeof options.strictNullHandling === 'boolean' ? options.strictNullHandling : defaults.strictNullHandling;

    if (str === '' || str === null || typeof str === 'undefined') {
        return options.plainObjects ? Object.create(null) : {};
    }

    var tempObj = typeof str === 'string' ? parseValues(str, options) : str;
    var obj = options.plainObjects ? Object.create(null) : {};

    // Iterate over the keys and setup the new object

    var keys = Object.keys(tempObj);
    for (var i = 0; i < keys.length; ++i) {
        var key = keys[i];
        var newObj = parseKeys(key, tempObj[key], options);
        obj = utils.merge(obj, newObj, options);
    }

    return utils.compact(obj);
};


/***/ }),

/***/ 1263:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var utils = __webpack_require__(1245);
var formats = __webpack_require__(1244);

var arrayPrefixGenerators = {
    brackets: function brackets(prefix) { // eslint-disable-line func-name-matching
        return prefix + '[]';
    },
    indices: function indices(prefix, key) { // eslint-disable-line func-name-matching
        return prefix + '[' + key + ']';
    },
    repeat: function repeat(prefix) { // eslint-disable-line func-name-matching
        return prefix;
    }
};

var toISO = Date.prototype.toISOString;

var defaults = {
    delimiter: '&',
    encode: true,
    encoder: utils.encode,
    encodeValuesOnly: false,
    serializeDate: function serializeDate(date) { // eslint-disable-line func-name-matching
        return toISO.call(date);
    },
    skipNulls: false,
    strictNullHandling: false
};

var stringify = function stringify( // eslint-disable-line func-name-matching
    object,
    prefix,
    generateArrayPrefix,
    strictNullHandling,
    skipNulls,
    encoder,
    filter,
    sort,
    allowDots,
    serializeDate,
    formatter,
    encodeValuesOnly
) {
    var obj = object;
    if (typeof filter === 'function') {
        obj = filter(prefix, obj);
    } else if (obj instanceof Date) {
        obj = serializeDate(obj);
    } else if (obj === null) {
        if (strictNullHandling) {
            return encoder && !encodeValuesOnly ? encoder(prefix, defaults.encoder) : prefix;
        }

        obj = '';
    }

    if (typeof obj === 'string' || typeof obj === 'number' || typeof obj === 'boolean' || utils.isBuffer(obj)) {
        if (encoder) {
            var keyValue = encodeValuesOnly ? prefix : encoder(prefix, defaults.encoder);
            return [formatter(keyValue) + '=' + formatter(encoder(obj, defaults.encoder))];
        }
        return [formatter(prefix) + '=' + formatter(String(obj))];
    }

    var values = [];

    if (typeof obj === 'undefined') {
        return values;
    }

    var objKeys;
    if (Array.isArray(filter)) {
        objKeys = filter;
    } else {
        var keys = Object.keys(obj);
        objKeys = sort ? keys.sort(sort) : keys;
    }

    for (var i = 0; i < objKeys.length; ++i) {
        var key = objKeys[i];

        if (skipNulls && obj[key] === null) {
            continue;
        }

        if (Array.isArray(obj)) {
            values = values.concat(stringify(
                obj[key],
                generateArrayPrefix(prefix, key),
                generateArrayPrefix,
                strictNullHandling,
                skipNulls,
                encoder,
                filter,
                sort,
                allowDots,
                serializeDate,
                formatter,
                encodeValuesOnly
            ));
        } else {
            values = values.concat(stringify(
                obj[key],
                prefix + (allowDots ? '.' + key : '[' + key + ']'),
                generateArrayPrefix,
                strictNullHandling,
                skipNulls,
                encoder,
                filter,
                sort,
                allowDots,
                serializeDate,
                formatter,
                encodeValuesOnly
            ));
        }
    }

    return values;
};

module.exports = function (object, opts) {
    var obj = object;
    var options = opts ? utils.assign({}, opts) : {};

    if (options.encoder !== null && options.encoder !== undefined && typeof options.encoder !== 'function') {
        throw new TypeError('Encoder has to be a function.');
    }

    var delimiter = typeof options.delimiter === 'undefined' ? defaults.delimiter : options.delimiter;
    var strictNullHandling = typeof options.strictNullHandling === 'boolean' ? options.strictNullHandling : defaults.strictNullHandling;
    var skipNulls = typeof options.skipNulls === 'boolean' ? options.skipNulls : defaults.skipNulls;
    var encode = typeof options.encode === 'boolean' ? options.encode : defaults.encode;
    var encoder = typeof options.encoder === 'function' ? options.encoder : defaults.encoder;
    var sort = typeof options.sort === 'function' ? options.sort : null;
    var allowDots = typeof options.allowDots === 'undefined' ? false : options.allowDots;
    var serializeDate = typeof options.serializeDate === 'function' ? options.serializeDate : defaults.serializeDate;
    var encodeValuesOnly = typeof options.encodeValuesOnly === 'boolean' ? options.encodeValuesOnly : defaults.encodeValuesOnly;
    if (typeof options.format === 'undefined') {
        options.format = formats['default'];
    } else if (!Object.prototype.hasOwnProperty.call(formats.formatters, options.format)) {
        throw new TypeError('Unknown format option provided.');
    }
    var formatter = formats.formatters[options.format];
    var objKeys;
    var filter;

    if (typeof options.filter === 'function') {
        filter = options.filter;
        obj = filter('', obj);
    } else if (Array.isArray(options.filter)) {
        filter = options.filter;
        objKeys = filter;
    }

    var keys = [];

    if (typeof obj !== 'object' || obj === null) {
        return '';
    }

    var arrayFormat;
    if (options.arrayFormat in arrayPrefixGenerators) {
        arrayFormat = options.arrayFormat;
    } else if ('indices' in options) {
        arrayFormat = options.indices ? 'indices' : 'repeat';
    } else {
        arrayFormat = 'indices';
    }

    var generateArrayPrefix = arrayPrefixGenerators[arrayFormat];

    if (!objKeys) {
        objKeys = Object.keys(obj);
    }

    if (sort) {
        objKeys.sort(sort);
    }

    for (var i = 0; i < objKeys.length; ++i) {
        var key = objKeys[i];

        if (skipNulls && obj[key] === null) {
            continue;
        }

        keys = keys.concat(stringify(
            obj[key],
            key,
            generateArrayPrefix,
            strictNullHandling,
            skipNulls,
            encode ? encoder : null,
            filter,
            sort,
            allowDots,
            serializeDate,
            formatter,
            encodeValuesOnly
        ));
    }

    var joined = keys.join(delimiter);
    var prefix = options.addQueryPrefix === true ? '?' : '';

    return joined.length > 0 ? prefix + joined : '';
};


/***/ }),

/***/ 1270:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__style_index_css__ = __webpack_require__(56);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__style_index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__style_index_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index_css__ = __webpack_require__(1280);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__index_css__);



/***/ }),

/***/ 1271:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _breadcrumb = __webpack_require__(511);

var _breadcrumb2 = _interopRequireDefault(_breadcrumb);

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _breadcrumbNameMap;

__webpack_require__(1270);

var _react = __webpack_require__(0);

var _react2 = _interopRequireDefault(_react);

var _reactRouterDom = __webpack_require__(45);

var _antd = __webpack_require__(210);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

var breadcrumbNameMap = (_breadcrumbNameMap = {
  '/': '活动列表',
  '/activity/add': '活动创建',
  '/entrance': '报名入口'
}, _defineProperty(_breadcrumbNameMap, '/activity/add', '创建活动'), _defineProperty(_breadcrumbNameMap, '/activity/update', '活动编辑'), _defineProperty(_breadcrumbNameMap, '/statistics', '统计分析'), _defineProperty(_breadcrumbNameMap, '/signlist', '报名列表'), _defineProperty(_breadcrumbNameMap, '/galaxy', '报名星系表'), _defineProperty(_breadcrumbNameMap, '/signdetail', '报名详情页'), _breadcrumbNameMap);

var Breadnav = function (_React$Component) {
  _inherits(Breadnav, _React$Component);

  function Breadnav(props, context) {
    _classCallCheck(this, Breadnav);

    return _possibleConstructorReturn(this, (Breadnav.__proto__ || Object.getPrototypeOf(Breadnav)).call(this, props, context));
  }

  _createClass(Breadnav, [{
    key: 'render',
    value: function render() {
      //const eventId = location.pathname.split('/')[3]
      var url = location.pathname.split('/')[1];
      var eventId = void 0;
      if (url === 'activity') {
        eventId = location.pathname.split('/')[3];
      } else {
        eventId = location.pathname.split('/')[2];
      }
      eventId = eventId ? eventId : "";
      var pathSnippets = location.pathname.split('/').filter(function (i) {
        return i;
      });
      var extraBreadcrumbItems = pathSnippets.map(function (_, index) {
        var url = '/' + pathSnippets.slice(0, index + 1).join('/');
        if (index < 1) {
          var currentUrl = location.pathname.split('/')[2];
          if (currentUrl == 'add') {
            url = '/activity/add';
          } else if (currentUrl == 'update') {
            url = '/activity/update';
          }
          return _react2.default.createElement(
            _breadcrumb2.default.Item,
            { key: url },
            _react2.default.createElement(
              _reactRouterDom.Link,
              { to: url + "/" + eventId },
              breadcrumbNameMap[url]
            )
          );
        }
      });

      var breadcrumbItems = [_react2.default.createElement(
        _breadcrumb2.default.Item,
        { key: '/' },
        _react2.default.createElement(
          _reactRouterDom.Link,
          { to: '/' },
          '\u6D3B\u52A8\u5217\u8868'
        )
      )].concat(extraBreadcrumbItems);
      return _react2.default.createElement(
        'div',
        { className: 'bread-nav' },
        _react2.default.createElement(
          _breadcrumb2.default,
          { separator: '>' },
          breadcrumbItems
        )
      );
    }
  }]);

  return Breadnav;
}(_react2.default.Component);

exports.default = Breadnav;

/***/ }),

/***/ 1280:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(1216)();
// imports


// module
exports.push([module.i, "/* stylelint-disable at-rule-empty-line-before,at-rule-name-space-after,at-rule-no-unknown */\n/* stylelint-disable no-duplicate-selectors */\n/* stylelint-disable declaration-bang-space-before,no-duplicate-selectors */\n/* stylelint-disable declaration-bang-space-before,no-duplicate-selectors,string-no-newline */\n.ant-breadcrumb {\n  font-family: \"Monospaced Number\", \"Chinese Quote\", -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"PingFang SC\", \"Hiragino Sans GB\", \"Microsoft YaHei\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n  line-height: 1.5;\n  color: rgba(0, 0, 0, 0.65);\n  -webkit-box-sizing: border-box;\n          box-sizing: border-box;\n  margin: 0;\n  padding: 0;\n  list-style: none;\n  color: rgba(0, 0, 0, 0.45);\n  font-size: 14px;\n}\n.ant-breadcrumb .anticon {\n  font-size: 12px;\n}\n.ant-breadcrumb a {\n  color: rgba(0, 0, 0, 0.45);\n  -webkit-transition: color .3s;\n  transition: color .3s;\n}\n.ant-breadcrumb a:hover {\n  color: #40a9ff;\n}\n.ant-breadcrumb > span:last-child {\n  color: rgba(0, 0, 0, 0.65);\n}\n.ant-breadcrumb > span:last-child .ant-breadcrumb-separator {\n  display: none;\n}\n.ant-breadcrumb-separator {\n  margin: 0 8px;\n  color: rgba(0, 0, 0, 0.45);\n}\n.ant-breadcrumb-link > .anticon + span {\n  margin-left: 4px;\n}\n", ""]);

// exports


/***/ }),

/***/ 1291:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _react = __webpack_require__(0);

var _react2 = _interopRequireDefault(_react);

var _reactRouterDom = __webpack_require__(45);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Sidebar = function (_React$Component) {
  _inherits(Sidebar, _React$Component);

  function Sidebar(props, context) {
    _classCallCheck(this, Sidebar);

    var _this = _possibleConstructorReturn(this, (Sidebar.__proto__ || Object.getPrototypeOf(Sidebar)).call(this, props, context));

    _this.state = {
      menu: [{ link: 'activity/update', name: '活动编辑' }, { link: 'entrance', name: '报名入口' }, { link: 'signlist', name: '报名列表' }, { link: 'statistics', name: '统计分析' }]
    };
    return _this;
  }

  _createClass(Sidebar, [{
    key: 'render',
    value: function render() {
      var url = location.pathname.split('/')[1];
      var eventId = void 0;
      if (url === 'activity') {
        eventId = location.pathname.split('/')[3];
        url = 'activity/update';
      } else {
        eventId = location.pathname.split('/')[2];
      }
      return _react2.default.createElement(
        'div',
        { className: 'sidebar' },
        _react2.default.createElement(
          'ul',
          null,
          this.state.menu.map(function (item, index) {
            return _react2.default.createElement(
              'li',
              { key: index, className: item.link === url ? 'menu' + index + ' active' : 'menu' + index },
              _react2.default.createElement(
                _reactRouterDom.Link,
                { to: "/" + item.link + "/" + eventId },
                _react2.default.createElement('i', { className: item.link + "-icon" }),
                _react2.default.createElement(
                  'span',
                  null,
                  item.name
                )
              )
            );
          })
        )
      );
    }
  }, {
    key: 'componentDidMount',
    value: function componentDidMount() {}
  }]);

  return Sidebar;
}(_react2.default.Component);

exports.default = Sidebar;

/***/ }),

/***/ 1313:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__style_index_css__ = __webpack_require__(56);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__style_index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__style_index_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index_css__ = __webpack_require__(1340);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__index_css__);



/***/ }),

/***/ 1316:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__style_index_css__ = __webpack_require__(56);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__style_index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__style_index_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index_css__ = __webpack_require__(1343);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__index_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__progress_style_css__ = __webpack_require__(1313);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__tooltip_style_css__ = __webpack_require__(516);


// style dependencies



/***/ }),

/***/ 1317:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.getCountryData = getCountryData;
exports.getProvinceData = getProvinceData;
exports.getCityData = getCityData;
exports.getActivityById = getActivityById;
exports.saveActivityData = saveActivityData;

var _axios = __webpack_require__(211);

var _axios2 = _interopRequireDefault(_axios);

var _qs = __webpack_require__(1258);

var _qs2 = _interopRequireDefault(_qs);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function getCountryData() {
  var url = "/area/country";
  return _axios2.default.post(url).then(function (res) {
    return Promise.resolve(res.data);
  });
}

function getProvinceData() {
  var url = "/area/province";
  return _axios2.default.post(url).then(function (res) {
    return Promise.resolve(res.data);
  });
}

function getCityData(city) {
  var url = "/area/city?name=" + city;
  return _axios2.default.get(url).then(function (res) {
    return Promise.resolve(res.data);
  });
}

function getActivityById(eventId) {
  var url = "/event/getEventById/" + eventId;
  var data = {
    id: eventId
  };
  data = _qs2.default.stringify(data);
  return _axios2.default.post(url, data, {
    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
  }).then(function (res) {
    return Promise.resolve(res.data);
  });
}

function saveActivityData(data) {
  data = _qs2.default.stringify(data);
  var url = "/event/saveEvent";
  return _axios2.default.post(url, data, {
    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
  }).then(function (res) {
    return Promise.resolve(res.data);
  });
}

/***/ }),

/***/ 1340:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(1216)();
// imports


// module
exports.push([module.i, "/* stylelint-disable at-rule-empty-line-before,at-rule-name-space-after,at-rule-no-unknown */\n/* stylelint-disable no-duplicate-selectors */\n/* stylelint-disable declaration-bang-space-before,no-duplicate-selectors */\n/* stylelint-disable declaration-bang-space-before,no-duplicate-selectors,string-no-newline */\n.ant-progress {\n  font-family: \"Monospaced Number\", \"Chinese Quote\", -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"PingFang SC\", \"Hiragino Sans GB\", \"Microsoft YaHei\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n  font-size: 14px;\n  line-height: 1.5;\n  color: rgba(0, 0, 0, 0.65);\n  -webkit-box-sizing: border-box;\n          box-sizing: border-box;\n  margin: 0;\n  padding: 0;\n  list-style: none;\n  display: inline-block;\n}\n.ant-progress-line {\n  width: 100%;\n  font-size: 14px;\n  position: relative;\n}\n.ant-progress-small.ant-progress-line,\n.ant-progress-small.ant-progress-line .ant-progress-text .anticon {\n  font-size: 12px;\n}\n.ant-progress-outer {\n  display: inline-block;\n  width: 100%;\n  margin-right: 0;\n  padding-right: 0;\n}\n.ant-progress-show-info .ant-progress-outer {\n  padding-right: calc(2em + 8px);\n  margin-right: calc(-2em - 8px);\n}\n.ant-progress-inner {\n  display: inline-block;\n  width: 100%;\n  background-color: #f5f5f5;\n  border-radius: 100px;\n  vertical-align: middle;\n  position: relative;\n}\n.ant-progress-circle-trail {\n  stroke: #f5f5f5;\n}\n.ant-progress-circle-path {\n  stroke: #1890ff;\n  -webkit-animation: ant-progress-appear 0.3s;\n          animation: ant-progress-appear 0.3s;\n}\n.ant-progress-success-bg,\n.ant-progress-bg {\n  border-radius: 100px;\n  background-color: #1890ff;\n  -webkit-transition: all 0.4s cubic-bezier(0.08, 0.82, 0.17, 1) 0s;\n  transition: all 0.4s cubic-bezier(0.08, 0.82, 0.17, 1) 0s;\n  position: relative;\n}\n.ant-progress-success-bg {\n  background-color: #52c41a;\n  position: absolute;\n  top: 0;\n  left: 0;\n}\n.ant-progress-text {\n  word-break: normal;\n  width: 2em;\n  text-align: left;\n  font-size: 1em;\n  margin-left: 8px;\n  vertical-align: middle;\n  display: inline-block;\n  color: rgba(0, 0, 0, 0.45);\n  line-height: 1;\n}\n.ant-progress-text .anticon {\n  font-size: 14px;\n}\n.ant-progress-status-active .ant-progress-bg:before {\n  content: \"\";\n  opacity: 0;\n  position: absolute;\n  top: 0;\n  left: 0;\n  right: 0;\n  bottom: 0;\n  background: #fff;\n  border-radius: 10px;\n  -webkit-animation: ant-progress-active 2.4s cubic-bezier(0.23, 1, 0.32, 1) infinite;\n          animation: ant-progress-active 2.4s cubic-bezier(0.23, 1, 0.32, 1) infinite;\n}\n.ant-progress-status-exception .ant-progress-bg {\n  background-color: #f5222d;\n}\n.ant-progress-status-exception .ant-progress-text {\n  color: #f5222d;\n}\n.ant-progress-status-exception .ant-progress-circle-path {\n  stroke: #f5222d;\n}\n.ant-progress-status-success .ant-progress-bg {\n  background-color: #52c41a;\n}\n.ant-progress-status-success .ant-progress-text {\n  color: #52c41a;\n}\n.ant-progress-status-success .ant-progress-circle-path {\n  stroke: #52c41a;\n}\n.ant-progress-circle .ant-progress-inner {\n  position: relative;\n  line-height: 1;\n  background-color: transparent;\n}\n.ant-progress-circle .ant-progress-text {\n  display: block;\n  position: absolute;\n  width: 100%;\n  text-align: center;\n  line-height: 1;\n  top: 50%;\n  -webkit-transform: translateY(-50%);\n      -ms-transform: translateY(-50%);\n          transform: translateY(-50%);\n  left: 0;\n  margin: 0;\n  color: rgba(0, 0, 0, 0.65);\n}\n.ant-progress-circle .ant-progress-text .anticon {\n  font-size: 1.16666667em;\n}\n.ant-progress-circle.ant-progress-status-exception .ant-progress-text {\n  color: #f5222d;\n}\n.ant-progress-circle.ant-progress-status-success .ant-progress-text {\n  color: #52c41a;\n}\n@-webkit-keyframes ant-progress-active {\n  0% {\n    opacity: 0.1;\n    width: 0;\n  }\n  20% {\n    opacity: 0.5;\n    width: 0;\n  }\n  100% {\n    opacity: 0;\n    width: 100%;\n  }\n}\n@keyframes ant-progress-active {\n  0% {\n    opacity: 0.1;\n    width: 0;\n  }\n  20% {\n    opacity: 0.5;\n    width: 0;\n  }\n  100% {\n    opacity: 0;\n    width: 100%;\n  }\n}\n", ""]);

// exports


/***/ }),

/***/ 1343:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(1216)();
// imports


// module
exports.push([module.i, "/* stylelint-disable at-rule-empty-line-before,at-rule-name-space-after,at-rule-no-unknown */\n/* stylelint-disable no-duplicate-selectors */\n/* stylelint-disable declaration-bang-space-before,no-duplicate-selectors */\n/* stylelint-disable declaration-bang-space-before,no-duplicate-selectors,string-no-newline */\n.ant-upload {\n  font-family: \"Monospaced Number\", \"Chinese Quote\", -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"PingFang SC\", \"Hiragino Sans GB\", \"Microsoft YaHei\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n  font-size: 14px;\n  line-height: 1.5;\n  color: rgba(0, 0, 0, 0.65);\n  -webkit-box-sizing: border-box;\n          box-sizing: border-box;\n  margin: 0;\n  padding: 0;\n  list-style: none;\n  outline: 0;\n}\n.ant-upload p {\n  margin: 0;\n}\n.ant-upload-btn {\n  display: block;\n  width: 100%;\n  outline: none;\n}\n.ant-upload input[type=\"file\"] {\n  cursor: pointer;\n}\n.ant-upload.ant-upload-select {\n  display: inline-block;\n}\n.ant-upload.ant-upload-select-picture-card {\n  border: 1px dashed #d9d9d9;\n  width: 104px;\n  height: 104px;\n  border-radius: 4px;\n  background-color: #fafafa;\n  text-align: center;\n  cursor: pointer;\n  -webkit-transition: border-color 0.3s ease;\n  transition: border-color 0.3s ease;\n  vertical-align: top;\n  margin-right: 8px;\n  margin-bottom: 8px;\n  display: table;\n}\n.ant-upload.ant-upload-select-picture-card > .ant-upload {\n  width: 100%;\n  height: 100%;\n  display: table-cell;\n  text-align: center;\n  vertical-align: middle;\n  padding: 8px;\n}\n.ant-upload.ant-upload-select-picture-card:hover {\n  border-color: #1890ff;\n}\n.ant-upload.ant-upload-drag {\n  border: 1px dashed #d9d9d9;\n  -webkit-transition: border-color 0.3s;\n  transition: border-color 0.3s;\n  cursor: pointer;\n  border-radius: 4px;\n  text-align: center;\n  width: 100%;\n  height: 100%;\n  position: relative;\n  padding: 16px 0;\n  background: #fafafa;\n}\n.ant-upload.ant-upload-drag.ant-upload-drag-hover:not(.ant-upload-disabled) {\n  border: 2px dashed #40a9ff;\n}\n.ant-upload.ant-upload-drag.ant-upload-disabled {\n  cursor: not-allowed;\n}\n.ant-upload.ant-upload-drag .ant-upload-btn {\n  display: table;\n  height: 100%;\n}\n.ant-upload.ant-upload-drag .ant-upload-drag-container {\n  display: table-cell;\n  vertical-align: middle;\n}\n.ant-upload.ant-upload-drag:not(.ant-upload-disabled):hover {\n  border-color: #40a9ff;\n}\n.ant-upload.ant-upload-drag p.ant-upload-drag-icon {\n  margin-bottom: 20px;\n}\n.ant-upload.ant-upload-drag p.ant-upload-drag-icon .anticon {\n  font-size: 48px;\n  color: #40a9ff;\n}\n.ant-upload.ant-upload-drag p.ant-upload-text {\n  font-size: 16px;\n  margin: 0 0 4px;\n  color: rgba(0, 0, 0, 0.85);\n}\n.ant-upload.ant-upload-drag p.ant-upload-hint {\n  font-size: 14px;\n  color: rgba(0, 0, 0, 0.45);\n}\n.ant-upload.ant-upload-drag .anticon-plus {\n  font-size: 30px;\n  -webkit-transition: all .3s;\n  transition: all .3s;\n  color: rgba(0, 0, 0, 0.25);\n}\n.ant-upload.ant-upload-drag .anticon-plus:hover {\n  color: rgba(0, 0, 0, 0.45);\n}\n.ant-upload.ant-upload-drag:hover .anticon-plus {\n  color: rgba(0, 0, 0, 0.45);\n}\n.ant-upload-list {\n  font-family: \"Monospaced Number\", \"Chinese Quote\", -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"PingFang SC\", \"Hiragino Sans GB\", \"Microsoft YaHei\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n  font-size: 14px;\n  line-height: 1.5;\n  color: rgba(0, 0, 0, 0.65);\n  -webkit-box-sizing: border-box;\n          box-sizing: border-box;\n  margin: 0;\n  padding: 0;\n  list-style: none;\n  zoom: 1;\n}\n.ant-upload-list:before,\n.ant-upload-list:after {\n  content: \"\";\n  display: table;\n}\n.ant-upload-list:after {\n  clear: both;\n}\n.ant-upload-list-item {\n  margin-top: 8px;\n  font-size: 14px;\n  position: relative;\n  height: 22px;\n}\n.ant-upload-list-item-name {\n  overflow: hidden;\n  text-overflow: ellipsis;\n  white-space: nowrap;\n  padding-left: 22px;\n  width: 100%;\n  display: inline-block;\n}\n.ant-upload-list-item-info {\n  height: 100%;\n  padding: 0 12px 0 4px;\n  -webkit-transition: background-color 0.3s;\n  transition: background-color 0.3s;\n}\n.ant-upload-list-item-info > span {\n  display: block;\n}\n.ant-upload-list-item-info .anticon-loading,\n.ant-upload-list-item-info .anticon-paper-clip {\n  font-size: 14px;\n  color: rgba(0, 0, 0, 0.45);\n  position: absolute;\n  top: 5px;\n}\n.ant-upload-list-item .anticon-cross {\n  display: inline-block;\n  font-size: 12px;\n  font-size: 10px \\9;\n  -webkit-transform: scale(0.83333333) rotate(0deg);\n      -ms-transform: scale(0.83333333) rotate(0deg);\n          transform: scale(0.83333333) rotate(0deg);\n  -webkit-transition: all .3s;\n  transition: all .3s;\n  opacity: 0;\n  cursor: pointer;\n  position: absolute;\n  top: 0;\n  right: 4px;\n  color: rgba(0, 0, 0, 0.45);\n  line-height: 22px;\n}\n:root .ant-upload-list-item .anticon-cross {\n  font-size: 12px;\n}\n.ant-upload-list-item .anticon-cross:hover {\n  color: rgba(0, 0, 0, 0.65);\n}\n.ant-upload-list-item:hover .ant-upload-list-item-info {\n  background-color: #e6f7ff;\n}\n.ant-upload-list-item:hover .anticon-cross {\n  opacity: 1;\n}\n.ant-upload-list-item-error,\n.ant-upload-list-item-error .anticon-paper-clip,\n.ant-upload-list-item-error .ant-upload-list-item-name {\n  color: #f5222d;\n}\n.ant-upload-list-item-error .anticon-cross {\n  opacity: 1;\n  color: #f5222d !important;\n}\n.ant-upload-list-item-progress {\n  line-height: 0;\n  font-size: 14px;\n  position: absolute;\n  width: 100%;\n  bottom: -12px;\n  padding-left: 26px;\n}\n.ant-upload-list-picture .ant-upload-list-item,\n.ant-upload-list-picture-card .ant-upload-list-item {\n  padding: 8px;\n  border-radius: 4px;\n  border: 1px solid #d9d9d9;\n  height: 66px;\n  position: relative;\n}\n.ant-upload-list-picture .ant-upload-list-item:hover,\n.ant-upload-list-picture-card .ant-upload-list-item:hover {\n  background: transparent;\n}\n.ant-upload-list-picture .ant-upload-list-item-error,\n.ant-upload-list-picture-card .ant-upload-list-item-error {\n  border-color: #f5222d;\n}\n.ant-upload-list-picture .ant-upload-list-item-info,\n.ant-upload-list-picture-card .ant-upload-list-item-info {\n  padding: 0;\n}\n.ant-upload-list-picture .ant-upload-list-item:hover .ant-upload-list-item-info,\n.ant-upload-list-picture-card .ant-upload-list-item:hover .ant-upload-list-item-info {\n  background: transparent;\n}\n.ant-upload-list-picture .ant-upload-list-item-uploading,\n.ant-upload-list-picture-card .ant-upload-list-item-uploading {\n  border-style: dashed;\n}\n.ant-upload-list-picture .ant-upload-list-item-thumbnail,\n.ant-upload-list-picture-card .ant-upload-list-item-thumbnail {\n  width: 48px;\n  height: 48px;\n  position: absolute;\n  top: 8px;\n  left: 8px;\n  text-align: center;\n}\n.ant-upload-list-picture .ant-upload-list-item-icon,\n.ant-upload-list-picture-card .ant-upload-list-item-icon {\n  color: rgba(0, 0, 0, 0.25);\n  font-size: 36px;\n  position: absolute;\n  top: 50%;\n  left: 50%;\n  margin-top: -18px;\n  margin-left: -18px;\n}\n.ant-upload-list-picture .ant-upload-list-item-thumbnail img,\n.ant-upload-list-picture-card .ant-upload-list-item-thumbnail img {\n  width: 48px;\n  height: 48px;\n  display: block;\n  overflow: hidden;\n}\n.ant-upload-list-picture .ant-upload-list-item-thumbnail.anticon:before,\n.ant-upload-list-picture-card .ant-upload-list-item-thumbnail.anticon:before {\n  line-height: 48px;\n  font-size: 24px;\n  color: rgba(0, 0, 0, 0.45);\n}\n.ant-upload-list-picture .ant-upload-list-item-name,\n.ant-upload-list-picture-card .ant-upload-list-item-name {\n  overflow: hidden;\n  text-overflow: ellipsis;\n  white-space: nowrap;\n  margin: 0 0 0 8px;\n  line-height: 44px;\n  -webkit-transition: all .3s;\n  transition: all .3s;\n  padding-left: 48px;\n  padding-right: 8px;\n  max-width: 100%;\n  display: inline-block;\n  -webkit-box-sizing: border-box;\n          box-sizing: border-box;\n}\n.ant-upload-list-picture .ant-upload-list-item-uploading .ant-upload-list-item-name,\n.ant-upload-list-picture-card .ant-upload-list-item-uploading .ant-upload-list-item-name {\n  line-height: 28px;\n}\n.ant-upload-list-picture .ant-upload-list-item-progress,\n.ant-upload-list-picture-card .ant-upload-list-item-progress {\n  padding-left: 56px;\n  margin-top: 0;\n  bottom: 14px;\n  width: calc(100% - 24px);\n}\n.ant-upload-list-picture .anticon-cross,\n.ant-upload-list-picture-card .anticon-cross {\n  position: absolute;\n  right: 8px;\n  top: 8px;\n  line-height: 1;\n}\n.ant-upload-list-picture-card {\n  display: inline;\n}\n.ant-upload-list-picture-card.ant-upload-list:after {\n  display: none;\n}\n.ant-upload-list-picture-card .ant-upload-list-item {\n  float: left;\n  width: 104px;\n  height: 104px;\n  margin: 0 8px 8px 0;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-info {\n  height: 100%;\n  position: relative;\n  overflow: hidden;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-info:before {\n  content: ' ';\n  position: absolute;\n  z-index: 1;\n  background-color: rgba(0, 0, 0, 0.5);\n  -webkit-transition: all .3s;\n  transition: all .3s;\n  width: 100%;\n  height: 100%;\n  opacity: 0;\n}\n.ant-upload-list-picture-card .ant-upload-list-item:hover .ant-upload-list-item-info:before {\n  opacity: 1;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-actions {\n  position: absolute;\n  left: 50%;\n  top: 50%;\n  -webkit-transform: translate(-50%, -50%);\n      -ms-transform: translate(-50%, -50%);\n          transform: translate(-50%, -50%);\n  z-index: 10;\n  white-space: nowrap;\n  opacity: 0;\n  -webkit-transition: all .3s;\n  transition: all .3s;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-actions .anticon-eye-o,\n.ant-upload-list-picture-card .ant-upload-list-item-actions .anticon-delete {\n  z-index: 10;\n  -webkit-transition: all .3s;\n  transition: all .3s;\n  cursor: pointer;\n  font-size: 16px;\n  width: 16px;\n  color: rgba(255, 255, 255, 0.85);\n  margin: 0 4px;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-actions .anticon-eye-o:hover,\n.ant-upload-list-picture-card .ant-upload-list-item-actions .anticon-delete:hover {\n  color: #fff;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-info:hover + .ant-upload-list-item-actions,\n.ant-upload-list-picture-card .ant-upload-list-item-actions:hover {\n  opacity: 1;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-thumbnail,\n.ant-upload-list-picture-card .ant-upload-list-item-thumbnail img {\n  display: block;\n  width: 100%;\n  height: 100%;\n  position: static;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-name {\n  margin: 8px 0 0;\n  padding: 0;\n  text-align: center;\n  line-height: 1.5;\n  display: none;\n}\n.ant-upload-list-picture-card .anticon-picture + .ant-upload-list-item-name {\n  display: block;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-uploading.ant-upload-list-item {\n  background-color: #fafafa;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-uploading .ant-upload-list-item-info {\n  height: auto;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-uploading .ant-upload-list-item-info:before,\n.ant-upload-list-picture-card .ant-upload-list-item-uploading .ant-upload-list-item-info .anticon-eye-o,\n.ant-upload-list-picture-card .ant-upload-list-item-uploading .ant-upload-list-item-info .anticon-delete {\n  display: none;\n}\n.ant-upload-list-picture-card .ant-upload-list-item-uploading-text {\n  margin-top: 18px;\n  color: rgba(0, 0, 0, 0.45);\n}\n.ant-upload-list-picture-card .ant-upload-list-item-progress {\n  padding-left: 0;\n  bottom: 32px;\n}\n.ant-upload-list .ant-upload-success-icon {\n  color: #52c41a;\n  font-weight: bold;\n}\n.ant-upload-list .ant-upload-animate-enter,\n.ant-upload-list .ant-upload-animate-leave,\n.ant-upload-list .ant-upload-animate-inline-enter,\n.ant-upload-list .ant-upload-animate-inline-leave {\n  -webkit-animation-duration: .3s;\n          animation-duration: .3s;\n  -webkit-animation-fill-mode: cubic-bezier(0.78, 0.14, 0.15, 0.86);\n          animation-fill-mode: cubic-bezier(0.78, 0.14, 0.15, 0.86);\n}\n.ant-upload-list .ant-upload-animate-enter {\n  -webkit-animation-name: uploadAnimateIn;\n          animation-name: uploadAnimateIn;\n}\n.ant-upload-list .ant-upload-animate-leave {\n  -webkit-animation-name: uploadAnimateOut;\n          animation-name: uploadAnimateOut;\n}\n.ant-upload-list .ant-upload-animate-inline-enter {\n  -webkit-animation-name: uploadAnimateInlineIn;\n          animation-name: uploadAnimateInlineIn;\n}\n.ant-upload-list .ant-upload-animate-inline-leave {\n  -webkit-animation-name: uploadAnimateInlineOut;\n          animation-name: uploadAnimateInlineOut;\n}\n@-webkit-keyframes uploadAnimateIn {\n  from {\n    height: 0;\n    margin: 0;\n    opacity: 0;\n    padding: 0;\n  }\n}\n@keyframes uploadAnimateIn {\n  from {\n    height: 0;\n    margin: 0;\n    opacity: 0;\n    padding: 0;\n  }\n}\n@-webkit-keyframes uploadAnimateOut {\n  to {\n    height: 0;\n    margin: 0;\n    padding: 0;\n    opacity: 0;\n  }\n}\n@keyframes uploadAnimateOut {\n  to {\n    height: 0;\n    margin: 0;\n    padding: 0;\n    opacity: 0;\n  }\n}\n@-webkit-keyframes uploadAnimateInlineIn {\n  from {\n    width: 0;\n    height: 0;\n    margin: 0;\n    opacity: 0;\n    padding: 0;\n  }\n}\n@keyframes uploadAnimateInlineIn {\n  from {\n    width: 0;\n    height: 0;\n    margin: 0;\n    opacity: 0;\n    padding: 0;\n  }\n}\n@-webkit-keyframes uploadAnimateInlineOut {\n  to {\n    width: 0;\n    height: 0;\n    margin: 0;\n    padding: 0;\n    opacity: 0;\n  }\n}\n@keyframes uploadAnimateInlineOut {\n  to {\n    width: 0;\n    height: 0;\n    margin: 0;\n    padding: 0;\n    opacity: 0;\n  }\n}\n", ""]);

// exports


/***/ }),

/***/ 1511:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.saveWXLogo = saveWXLogo;

function circleImg(url, ctx, dx, dy, sw, sy) {
  ctx.save();
  ctx.beginPath();
  ctx.arc(sw / 2 + dx, sy / 2 + dy, sw / 2, 0, Math.PI * 2, false);
  ctx.closePath();
  ctx.clip();

  ctx.drawImage(url, dx, dy, sw, sy);
  ctx.restore();
}

var LogoSyle = {
  wxlogo1: {
    width: 180,
    height: 180,
    logoPostion: [56, 56, 68, 68]
  },
  wxlogo2: {
    width: 180,
    height: 205,
    logoPostion: [71, 71, 38, 38]
  }
};

function saveWXLogo(name) {
  var oBody = document.getElementById(name);
  var pic1 = oBody.childNodes[0].src;
  var pic2 = oBody.childNodes[1].src;
  var title = "小瓜报名" + Date.parse(new Date());
  var canvas = document.getElementById(name + "-canvas");
  var img = new Image();
  img.crossOrigin = "anonymous";
  img.src = pic1;
  img.onload = function () {
    canvas.width = LogoSyle[name].width; //这个设置不能丢，否者会成为canvas默认的300*150的大小
    canvas.height = LogoSyle[name].height; //这个设置不能丢，否者会成为canvas默认的300*150的大小
    var ctx = canvas.getContext("2d");
    var logo = new Image();
    logo.crossOrigin = "anonymous";
    logo.src = pic2;
    ctx.fillStyle = "#fff";
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    ctx.drawImage(img, 0, 0, LogoSyle[name].width, LogoSyle[name].height);

    if (pic2) {
      logo.onload = function () {
        var position = LogoSyle[name].logoPostion;
        circleImg(logo, ctx, position[0], position[1], position[2], position[3]);
        var dataURL = canvas.toDataURL("image/jpeg");
        var aBtn = document.createElement("a");
        aBtn.href = dataURL;
        aBtn.download = title + ".jpg";
        oBody.appendChild(aBtn);
        aBtn.click();
        aBtn.remove();
      };
    } else {
      var dataURL = canvas.toDataURL("image/jpeg");
      var aBtn = document.createElement("a");
      aBtn.href = dataURL;
      aBtn.download = title + ".jpg";
      oBody.appendChild(aBtn);
      aBtn.click();
      aBtn.remove();
    }
  };
}

/***/ })

});