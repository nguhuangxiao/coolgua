webpackJsonp([6],{

/***/ 1221:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _react = __webpack_require__(0);

var _react2 = _interopRequireDefault(_react);

var _reactRouterDom = __webpack_require__(45);

var _signlist = __webpack_require__(1380);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var SignDetail = function (_React$Component) {
  _inherits(SignDetail, _React$Component);

  function SignDetail(props, context) {
    _classCallCheck(this, SignDetail);

    var _this = _possibleConstructorReturn(this, (SignDetail.__proto__ || Object.getPrototypeOf(SignDetail)).call(this, props, context));

    _this.state = {
      baseData: {},
      fieldData: []
    };
    return _this;
  }

  _createClass(SignDetail, [{
    key: 'render',
    value: function render() {
      var base = this.state.baseData;
      var eventId = this.props.match.params.id;
      var openId = this.props.match.params.openId;
      var link1 = "/signlist/" + eventId;
      var link2 = "/signdetail/" + eventId + "/" + openId;
      return _react2.default.createElement(
        'div',
        { className: 'wrapper' },
        _react2.default.createElement(
          'div',
          { className: 'containner' },
          _react2.default.createElement(
            'div',
            { className: 'bread-nav' },
            _react2.default.createElement(
              'div',
              { className: 'ant-breadcrumb' },
              _react2.default.createElement(
                'span',
                null,
                _react2.default.createElement(
                  'span',
                  { className: 'ant-breadcrumb-link' },
                  _react2.default.createElement(
                    _reactRouterDom.Link,
                    { to: '/' },
                    '\u6D3B\u52A8\u5217\u8868'
                  ),
                  _react2.default.createElement(
                    'span',
                    { className: 'ant-breadcrumb-separator' },
                    '>'
                  )
                )
              ),
              _react2.default.createElement(
                'span',
                null,
                _react2.default.createElement(
                  'span',
                  { className: 'ant-breadcrumb-link' },
                  _react2.default.createElement(
                    _reactRouterDom.Link,
                    { to: link1 },
                    '\u62A5\u540D\u5217\u8868'
                  ),
                  _react2.default.createElement(
                    'span',
                    { className: 'ant-breadcrumb-separator' },
                    '>'
                  )
                )
              ),
              _react2.default.createElement(
                'span',
                null,
                _react2.default.createElement(
                  'span',
                  { className: 'ant-breadcrumb-link' },
                  _react2.default.createElement(
                    _reactRouterDom.Link,
                    { to: link2 },
                    '\u62A5\u540D\u8BE6\u60C5\u9875'
                  ),
                  _react2.default.createElement(
                    'span',
                    { className: 'ant-breadcrumb-separator' },
                    '>'
                  )
                )
              )
            )
          )
        ),
        _react2.default.createElement(
          'div',
          { className: 'signdetail-main' },
          _react2.default.createElement(
            'div',
            { className: 'containner' },
            _react2.default.createElement(
              'div',
              { className: 'signdetail-section' },
              _react2.default.createElement(
                'div',
                { className: 'base-info' },
                _react2.default.createElement(
                  'div',
                  { className: 'img-box' },
                  base.photo ? _react2.default.createElement('img', { src: base.photo }) : _react2.default.createElement('img', { src: base.head_imgurl }),
                  _react2.default.createElement(
                    'div',
                    { className: 'person' },
                    _react2.default.createElement(
                      'p',
                      null,
                      '\u59D3\u540D\uFF1A',
                      base.name
                    ),
                    _react2.default.createElement(
                      'p',
                      null,
                      '\u9080\u8BF7\u4EBA\uFF1A',
                      base.inviter
                    ),
                    _react2.default.createElement(
                      'p',
                      null,
                      '\u624B\u673A\u53F7\uFF1A',
                      base.cellphone
                    ),
                    _react2.default.createElement(
                      'p',
                      null,
                      '\u62A5\u540D\u65F6\u95F4\uFF1A',
                      base.create_time
                    ),
                    _react2.default.createElement(
                      'p',
                      null,
                      '\u95E8\u7968\u53F7\uFF1A',
                      base.ticket_num
                    )
                  )
                ),
                _react2.default.createElement(
                  'div',
                  { className: 'ticket-box' },
                  _react2.default.createElement(
                    'div',
                    null,
                    '\u95E8\u7968'
                  ),
                  _react2.default.createElement('img', { src: base.ticket_num_url, className: 'ticket' })
                )
              ),
              _react2.default.createElement(
                'ul',
                { className: 'chart-ul' },
                _react2.default.createElement(
                  'li',
                  null,
                  '\u53D7\u9080\u597D\u53CB\uFF1A',
                  _react2.default.createElement(
                    'span',
                    null,
                    base.inviteNum
                  )
                ),
                _react2.default.createElement(
                  'li',
                  null,
                  '\u9080\u8BF7\u6392\u884C\uFF1A',
                  _react2.default.createElement(
                    'span',
                    null,
                    base.inviteRank
                  )
                ),
                _react2.default.createElement(
                  'li',
                  null,
                  '\u5F71\u54CD\u529B\u6307\u6570\uFF1A',
                  _react2.default.createElement(
                    'span',
                    null,
                    base.effect
                  )
                ),
                _react2.default.createElement(
                  'li',
                  null,
                  '\u5F71\u54CD\u529B\u6392\u884C\uFF1A',
                  _react2.default.createElement(
                    'span',
                    null,
                    base.effectRank
                  )
                ),
                _react2.default.createElement(
                  'li',
                  null,
                  '\u5206\u4EAB\u6B21\u6570\uFF1A',
                  _react2.default.createElement(
                    'span',
                    null,
                    base.shareNum
                  )
                )
              ),
              _react2.default.createElement(
                'div',
                { className: 'info-list' },
                this.state.fieldData.map(function (item, index) {
                  return item.typeId == 0 ? _react2.default.createElement(
                    'div',
                    { className: 'list', key: index },
                    item.chinese,
                    item.required ? _react2.default.createElement(
                      'span',
                      { className: 'must' },
                      '*'
                    ) : "",
                    '\uFF1A',
                    item.data
                  ) : item.typeId == 1 ? _react2.default.createElement(
                    'div',
                    { className: 'list', key: index },
                    _react2.default.createElement(
                      'p',
                      null,
                      item.chinese,
                      item.required ? _react2.default.createElement(
                        'span',
                        { className: 'must' },
                        '*'
                      ) : ""
                    ),
                    _react2.default.createElement('textarea', { className: 'textarea-div', readOnly: '', disabled: 'disabled', defaultValue: item.data })
                  ) : item.typeId == 4 ? _react2.default.createElement(
                    'div',
                    { className: 'list', key: index },
                    _react2.default.createElement(
                      'p',
                      null,
                      item.chinese,
                      item.required ? _react2.default.createElement(
                        'span',
                        { className: 'must' },
                        '*'
                      ) : ""
                    ),
                    _react2.default.createElement(
                      'ul',
                      { className: 'dictionary-ul' },
                      _react2.default.createElement(
                        'li',
                        null,
                        item.data
                      )
                    )
                  ) : item.typeId == 5 ? _react2.default.createElement(
                    'div',
                    { className: 'list', key: index },
                    _react2.default.createElement(
                      'p',
                      null,
                      item.chinese,
                      item.required ? _react2.default.createElement(
                        'span',
                        { className: 'must' },
                        '*'
                      ) : ""
                    ),
                    _react2.default.createElement(
                      'ul',
                      { className: 'dictionary-ul' },
                      item.dictionary.split('##@@##').map(function (ret, pn) {
                        return _react2.default.createElement(
                          'li',
                          { key: pn },
                          ret
                        );
                      })
                    )
                  ) : "";
                })
              )
            )
          )
        )
      );
    }
  }, {
    key: 'componentDidMount',
    value: function componentDidMount() {
      var _this2 = this;

      var eventId = this.props.match.params.id;
      var openId = this.props.match.params.openId;
      var result = (0, _signlist.getSignInfo)(eventId, openId);
      result.then(function (res) {
        var obj = {};
        for (var key in res.map.topData) {
          var item = res.map.topData[key];
          obj[item.english] = item.data;
        }
        _this2.setState({
          baseData: obj,
          fieldData: res.map.customData
        }, function () {
          console.log(_this2.state);
        });
      });
    }
  }]);

  return SignDetail;
}(_react2.default.Component);

exports.default = SignDetail;

/***/ }),

/***/ 1380:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.dropExcellData = dropExcellData;
exports.getSignInfo = getSignInfo;

var _axios = __webpack_require__(211);

var _axios2 = _interopRequireDefault(_axios);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function dropExcellData(that, eventId, ids) {
  var url = "/event/exportReg/" + eventId;
  var form = that.refs.download;
  form.action = url;
  var input = document.createElement("input");
  input.type = "hidden";
  input.name = "ids";
  input.value = JSON.stringify(ids);
  form.appendChild(input);
  form.submit();
  form.innerHTML = "";
}

function getSignInfo(eventId, openId) {
  var url = "/event/register/" + eventId + "/" + openId;
  return _axios2.default.post(url).then(function (res) {
    return Promise.resolve(res.data);
  });
}

/***/ })

});