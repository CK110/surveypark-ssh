var requirejs, require, define;
(function(ba) {
	function G(e) {
		return "[object Function]" === K.call(e)
	}

	function H(e) {
		return "[object Array]" === K.call(e)
	}

	function v(e, t) {
		if (e) {
			var i;
			for (i = 0; i < e.length && (!e[i] || !t(e[i], i, e)); i += 1);
		}
	}

	function T(e, t) {
		if (e) {
			var i;
			for (i = e.length - 1; - 1 < i && (!e[i] || !t(e[i], i, e)); i -= 1);
		}
	}

	function t(e, t) {
		return fa.call(e, t)
	}

	function m(e, i) {
		return t(e, i) && e[i]
	}

	function B(e, i) {
		for (var r in e)
			if (t(e, r) && i(e[r], r)) break
	}

	function U(e, i, r, n) {
		i && B(i, function(i, o) {
			if (r || !t(e, o)) n && "object" === typeof i && i && !H(i) && !G(i) && !(i instanceof RegExp) ? (e[o] || (e[o] = {}), U(e[o], i, r, n)) : e[o] = i
		});
		return e
	}

	function u(e, t) {
		return function() {
			return t.apply(e, arguments)
		}
	}

	function ca(e) {
		throw e
	}

	function da(e) {
		if (!e) return e;
		var t = ba;
		v(e.split("."), function(e) {
			t = t[e]
		});
		return t
	}

	function C(e, t, i, r) {
		t = Error(t + "\nhttp://requirejs.org/docs/errors.html#" + e);
		t.requireType = e;
		t.requireModules = r;
		i && (t.originalError = i);
		return t
	}

	function ga(e) {
		function i(e, t, i) {
			var r, n, o, a, s, u, c, d, t = t && t.split("/"),
				f = D.map,
				p = f && f["*"];
			if (e) {
				e = e.split("/");
				n = e.length - 1;
				D.nodeIdCompat && Q.test(e[n]) && (e[n] = e[n].replace(Q, ""));
				"." === e[0].charAt(0) && t && (n = t.slice(0, t.length - 1), e = n.concat(e));
				n = e;
				for (o = 0; o < n.length; o++)
					if (a = n[o], "." === a) n.splice(o, 1), o -= 1;
					else if (".." === a && !(0 === o || 1 == o && ".." === n[2] || ".." === n[o - 1]) && 0 < o) n.splice(o - 1, 2), o -= 2;
				e = e.join("/")
			}
			if (i && f && (t || p)) {
				n = e.split("/");
				o = n.length;
				e: for (; 0 < o; o -= 1) {
					s = n.slice(0, o).join("/");
					if (t)
						for (a = t.length; 0 < a; a -= 1)
							if (i = m(f, t.slice(0, a).join("/")))
								if (i = m(i, s)) {
									r = i;
									u = o;
									break e
								}!c && (p && m(p, s)) && (c = m(p, s), d = o)
				}!r && c && (r = c, u = d);
				r && (n.splice(0, u, r), e = n.join("/"))
			}
			return (r = m(D.pkgs, e)) ? r : e
		}

		function r(e) {
			z && v(document.getElementsByTagName("script"), function(t) {
				if (t.getAttribute("data-requiremodule") === e && t.getAttribute("data-requirecontext") === k.contextName) return t.parentNode.removeChild(t), !0
			})
		}

		function n(e) {
			var t = m(D.paths, e);
			if (t && H(t) && 1 < t.length) return t.shift(), k.require.undef(e), k.makeRequire(null, {
				skipMap: !0
			})([e]), !0
		}

		function o(e) {
			var t, i = e ? e.indexOf("!") : -1; - 1 < i && (t = e.substring(0, i), e = e.substring(i + 1, e.length));
			return [t, e]
		}

		function a(e, t, r, n) {
			var a, s, u = null,
				c = t ? t.name : null,
				d = e,
				f = !0,
				p = "";
			e || (f = !1, e = "_@r" + (P += 1));
			e = o(e);
			u = e[0];
			e = e[1];
			u && (u = i(u, c, n), s = m(_, u));
			e && (u ? p = s && s.normalize ? s.normalize(e, function(e) {
				return i(e, c, n)
			}) : -1 === e.indexOf("!") ? i(e, c, n) : e : (p = i(e, c, n), e = o(p), u = e[0], p = e[1], r = !0, a = k.nameToUrl(p)));
			r = u && !s && !r ? "_unnormalized" + (J += 1) : "";
			return {
				prefix: u,
				name: p,
				parentMap: t,
				unnormalized: !!r,
				url: a,
				originalName: d,
				isDefine: f,
				id: (u ? u + "!" + p : p) + r
			}
		}

		function s(e) {
			var t = e.id,
				i = m(S, t);
			i || (i = S[t] = new k.Module(e));
			return i
		}

		function c(e, i, r) {
			var n = e.id,
				o = m(S, n);
			if (t(_, n) && (!o || o.defineEmitComplete)) "defined" === i && r(_[n]);
			else if (o = s(e), o.error && "error" === i) r(o.error);
			else o.on(i, r)
		}

		function d(e, t) {
			var i = e.requireModules,
				r = !1;
			if (t) t(e);
			else if (v(i, function(t) {
				if (t = m(S, t)) t.error = e, t.events.error && (r = !0, t.emit("error", e))
			}), !r) g.onError(e)
		}

		function f() {
			R.length && (ha.apply(O, [O.length, 0].concat(R)), R = [])
		}

		function p(e) {
			delete S[e];
			delete A[e]
		}

		function l(e, t, i) {
			var r = e.map.id;
			e.error ? e.emit("error", e.error) : (t[r] = !0, v(e.depMaps, function(r, n) {
				var o = r.id,
					a = m(S, o);
				a && (!e.depMatched[n] && !i[o]) && (m(t, o) ? (e.defineDep(n, _[o]), e.check()) : l(a, t, i))
			}), i[r] = !0)
		}

		function h() {
			var e, t, i = (e = 1e3 * D.waitSeconds) && k.startTime + e < (new Date).getTime(),
				o = [],
				a = [],
				s = !1,
				u = !0;
			if (!q) {
				q = !0;
				B(A, function(e) {
					var c = e.map,
						d = c.id;
					if (e.enabled && (c.isDefine || a.push(e), !e.error))
						if (!e.inited && i) n(d) ? s = t = !0 : (o.push(d), r(d));
						else if (!e.inited && (e.fetched && c.isDefine) && (s = !0, !c.prefix)) return u = !1
				});
				if (i && o.length) return e = C("timeout", "Load timeout for modules: " + o, null, o), e.contextName = k.contextName, d(e);
				u && v(a, function(e) {
					l(e, {}, {})
				});
				if ((!i || t) && s)
					if ((z || ea) && !w) w = setTimeout(function() {
						w = 0;
						h()
					}, 50);
				q = !1
			}
		}

		function b(e) {
			t(_, e[0]) || s(a(e[0], null, !0)).init(e[1], e[2])
		}

		function y(e) {
			var e = e.currentTarget || e.srcElement,
				t = k.onScriptLoad;
			e.detachEvent && !Y ? e.detachEvent("onreadystatechange", t) : e.removeEventListener("load", t, !1);
			t = k.onScriptError;
			(!e.detachEvent || Y) && e.removeEventListener("error", t, !1);
			return {
				node: e,
				id: e && e.getAttribute("data-requiremodule")
			}
		}

		function x() {
			var e;
			for (f(); O.length;) {
				e = O.shift();
				if (null === e[0]) return d(C("mismatch", "Mismatched anonymous define() module: " + e[e.length - 1]));
				b(e)
			}
		}
		var q, j, k, E, w, D = {
				waitSeconds: 7,
				baseUrl: "./",
				paths: {},
				bundles: {},
				pkgs: {},
				shim: {},
				config: {}
			},
			S = {},
			A = {},
			L = {},
			O = [],
			_ = {},
			F = {},
			I = {},
			P = 1,
			J = 1;
		E = {
			require: function(e) {
				return e.require ? e.require : e.require = k.makeRequire(e.map)
			},
			exports: function(e) {
				e.usingExports = !0;
				if (e.map.isDefine) return e.exports ? _[e.map.id] = e.exports : e.exports = _[e.map.id] = {}
			},
			module: function(e) {
				return e.module ? e.module : e.module = {
					id: e.map.id,
					uri: e.map.url,
					config: function() {
						return m(D.config, e.map.id) || {}
					},
					exports: e.exports || (e.exports = {})
				}
			}
		};
		j = function(e) {
			this.events = m(L, e.id) || {};
			this.map = e;
			this.shim = m(D.shim, e.id);
			this.depExports = [];
			this.depMaps = [];
			this.depMatched = [];
			this.pluginMaps = {};
			this.depCount = 0
		};
		j.prototype = {
			init: function(e, t, i, r) {
				r = r || {};
				if (!this.inited) {
					this.factory = t;
					if (i) this.on("error", i);
					else this.events.error && (i = u(this, function(e) {
						this.emit("error", e)
					}));
					this.depMaps = e && e.slice(0);
					this.errback = i;
					this.inited = !0;
					this.ignore = r.ignore;
					r.enabled || this.enabled ? this.enable() : this.check()
				}
			},
			defineDep: function(e, t) {
				this.depMatched[e] || (this.depMatched[e] = !0, this.depCount -= 1, this.depExports[e] = t)
			},
			fetch: function() {
				if (!this.fetched) {
					this.fetched = !0;
					k.startTime = (new Date).getTime();
					var e = this.map;
					if (this.shim) k.makeRequire(this.map, {
						enableBuildCallback: !0
					})(this.shim.deps || [], u(this, function() {
						return e.prefix ? this.callPlugin() : this.load()
					}));
					else return e.prefix ? this.callPlugin() : this.load()
				}
			},
			load: function() {
				var e = this.map.url;
				F[e] || (F[e] = !0, k.load(this.map.id, e))
			},
			check: function() {
				if (this.enabled && !this.enabling) {
					var e, t, i = this.map.id;
					t = this.depExports;
					var r = this.exports,
						n = this.factory;
					if (this.inited)
						if (this.error) this.emit("error", this.error);
						else {
							if (!this.defining) {
								this.defining = !0;
								if (1 > this.depCount && !this.defined) {
									if (G(n)) {
										if (this.events.error && this.map.isDefine || g.onError !== ca) try {
											r = k.execCb(i, n, t, r)
										} catch (o) {
											e = o
										} else r = k.execCb(i, n, t, r);
										this.map.isDefine && void 0 === r && ((t = this.module) ? r = t.exports : this.usingExports && (r = this.exports));
										if (e) return e.requireMap = this.map, e.requireModules = this.map.isDefine ? [this.map.id] : null, e.requireType = this.map.isDefine ? "define" : "require", d(this.error = e)
									} else r = n;
									this.exports = r;
									if (this.map.isDefine && !this.ignore && (_[i] = r, g.onResourceLoad)) g.onResourceLoad(k, this.map, this.depMaps);
									p(i);
									this.defined = !0
								}
								this.defining = !1;
								this.defined && !this.defineEmitted && (this.defineEmitted = !0, this.emit("defined", this.exports), this.defineEmitComplete = !0)
							}
						} else this.fetch()
				}
			},
			callPlugin: function() {
				var e = this.map,
					r = e.id,
					n = a(e.prefix);
				this.depMaps.push(n);
				c(n, "defined", u(this, function(n) {
					var o, f;
					f = m(I, this.map.id);
					var l = this.map.name,
						h = this.map.parentMap ? this.map.parentMap.name : null,
						b = k.makeRequire(e.parentMap, {
							enableBuildCallback: !0
						});
					if (this.map.unnormalized) {
						if (n.normalize && (l = n.normalize(l, function(e) {
							return i(e, h, !0)
						}) || ""), n = a(e.prefix + "!" + l, this.map.parentMap), c(n, "defined", u(this, function(e) {
							this.init([], function() {
								return e
							}, null, {
								enabled: !0,
								ignore: !0
							})
						})), f = m(S, n.id)) {
							this.depMaps.push(n);
							if (this.events.error) f.on("error", u(this, function(e) {
								this.emit("error", e)
							}));
							f.enable()
						}
					} else f ? (this.map.url = k.nameToUrl(f), this.load()) : (o = u(this, function(e) {
						this.init([], function() {
							return e
						}, null, {
							enabled: !0
						})
					}), o.error = u(this, function(e) {
						this.inited = !0;
						this.error = e;
						e.requireModules = [r];
						B(S, function(e) {
							0 === e.map.id.indexOf(r + "_unnormalized") && p(e.map.id)
						});
						d(e)
					}), o.fromText = u(this, function(i, n) {
						var u = e.name,
							c = a(u),
							f = M;
						n && (i = n);
						f && (M = !1);
						s(c);
						t(D.config, r) && (D.config[u] = D.config[r]);
						try {
							g.exec(i)
						} catch (p) {
							return d(C("fromtexteval", "fromText eval for " + r + " failed: " + p, p, [r]))
						}
						f && (M = !0);
						this.depMaps.push(c);
						k.completeLoad(u);
						b([u], o)
					}), n.load(e.name, b, o, D))
				}));
				k.enable(n, this);
				this.pluginMaps[n.id] = n
			},
			enable: function() {
				A[this.map.id] = this;
				this.enabling = this.enabled = !0;
				v(this.depMaps, u(this, function(e, i) {
					var r, n;
					if ("string" === typeof e) {
						e = a(e, this.map.isDefine ? this.map : this.map.parentMap, !1, !this.skipMap);
						this.depMaps[i] = e;
						if (r = m(E, e.id)) {
							this.depExports[i] = r(this);
							return
						}
						this.depCount += 1;
						c(e, "defined", u(this, function(e) {
							this.defineDep(i, e);
							this.check()
						}));
						this.errback && c(e, "error", u(this, this.errback))
					}
					r = e.id;
					n = S[r];
					!t(E, r) && (n && !n.enabled) && k.enable(e, this)
				}));
				B(this.pluginMaps, u(this, function(e) {
					var t = m(S, e.id);
					t && !t.enabled && k.enable(e, this)
				}));
				this.enabling = !1;
				this.check()
			},
			on: function(e, t) {
				var i = this.events[e];
				i || (i = this.events[e] = []);
				i.push(t)
			},
			emit: function(e, t) {
				v(this.events[e], function(e) {
					e(t)
				});
				"error" === e && delete this.events[e]
			}
		};
		k = {
			config: D,
			contextName: e,
			registry: S,
			defined: _,
			urlFetched: F,
			defQueue: O,
			Module: j,
			makeModuleMap: a,
			nextTick: g.nextTick,
			onError: d,
			configure: function(e) {
				e.baseUrl && "/" !== e.baseUrl.charAt(e.baseUrl.length - 1) && (e.baseUrl += "/");
				var t = D.shim,
					i = {
						paths: !0,
						bundles: !0,
						config: !0,
						map: !0
					};
				B(e, function(e, t) {
					i[t] ? (D[t] || (D[t] = {}), U(D[t], e, !0, !0)) : D[t] = e
				});
				e.bundles && B(e.bundles, function(e, t) {
					v(e, function(e) {
						e !== t && (I[e] = t)
					})
				});
				e.shim && (B(e.shim, function(e, i) {
					H(e) && (e = {
						deps: e
					});
					if ((e.exports || e.init) && !e.exportsFn) e.exportsFn = k.makeShimExports(e);
					t[i] = e
				}), D.shim = t);
				e.packages && v(e.packages, function(e) {
					var t, e = "string" === typeof e ? {
						name: e
					} : e;
					t = e.name;
					e.location && (D.paths[t] = e.location);
					D.pkgs[t] = e.name + "/" + (e.main || "main").replace(ia, "").replace(Q, "")
				});
				B(S, function(e, t) {
					!e.inited && !e.map.unnormalized && (e.map = a(t))
				});
				if (e.deps || e.callback) k.require(e.deps || [], e.callback)
			},
			makeShimExports: function(e) {
				return function() {
					var t;
					e.init && (t = e.init.apply(ba, arguments));
					return t || e.exports && da(e.exports)
				}
			},
			makeRequire: function(n, o) {
				function u(i, r, c) {
					var f, p;
					o.enableBuildCallback && (r && G(r)) && (r.__requireJsBuild = !0);
					if ("string" === typeof i) {
						if (G(r)) return d(C("requireargs", "Invalid require call"), c);
						if (n && t(E, i)) return E[i](S[n.id]);
						if (g.get) return g.get(k, i, n, u);
						f = a(i, n, !1, !0);
						f = f.id;
						return !t(_, f) ? d(C("notloaded", 'Module name "' + f + '" has not been loaded yet for context: ' + e + (n ? "" : ". Use require([])"))) : _[f]
					}
					x();
					k.nextTick(function() {
						x();
						p = s(a(null, n));
						p.skipMap = o.skipMap;
						p.init(i, r, c, {
							enabled: !0
						});
						h()
					});
					return u
				}
				o = o || {};
				U(u, {
					isBrowser: z,
					toUrl: function(e) {
						var t, r = e.lastIndexOf("."),
							o = e.split("/")[0];
						if (-1 !== r && (!("." === o || ".." === o) || 1 < r)) t = e.substring(r, e.length), e = e.substring(0, r);
						return k.nameToUrl(i(e, n && n.id, !0), t, !0)
					},
					defined: function(e) {
						return t(_, a(e, n, !1, !0).id)
					},
					specified: function(e) {
						e = a(e, n, !1, !0).id;
						return t(_, e) || t(S, e)
					}
				});
				n || (u.undef = function(e) {
					f();
					var t = a(e, n, !0),
						i = m(S, e);
					r(e);
					delete _[e];
					delete F[t.url];
					delete L[e];
					T(O, function(t, i) {
						t[0] === e && O.splice(i, 1)
					});
					i && (i.events.defined && (L[e] = i.events), p(e))
				});
				return u
			},
			enable: function(e) {
				m(S, e.id) && s(e).enable()
			},
			completeLoad: function(e) {
				var i, r, o = m(D.shim, e) || {},
					a = o.exports;
				for (f(); O.length;) {
					r = O.shift();
					if (null === r[0]) {
						r[0] = e;
						if (i) break;
						i = !0
					} else r[0] === e && (i = !0);
					b(r)
				}
				r = m(S, e);
				if (!i && !t(_, e) && r && !r.inited) {
					if (D.enforceDefine && (!a || !da(a))) return n(e) ? void 0 : d(C("nodefine", "No define call for " + e, null, [e]));
					b([e, o.deps || [], o.exportsFn])
				}
				h()
			},
			nameToUrl: function(e, t, i) {
				var r, n, o;
				(r = m(D.pkgs, e)) && (e = r);
				if (r = m(I, e)) return k.nameToUrl(r, t, i);
				if (g.jsExtRegExp.test(e)) r = e + (t || "");
				else {
					r = D.paths;
					e = e.split("/");
					for (n = e.length; 0 < n; n -= 1)
						if (o = e.slice(0, n).join("/"), o = m(r, o)) {
							H(o) && (o = o[0]);
							e.splice(0, n, o);
							break
						}
					r = e.join("/");
					r += t || (/^data\:|\?/.test(r) || i ? "" : ".js");
					r = ("/" === r.charAt(0) || r.match(/^[\w\+\.\-]+:/) ? "" : D.baseUrl) + r
				}
				return D.urlArgs ? r + ((-1 === r.indexOf("?") ? "?" : "&") + D.urlArgs) : r
			},
			load: function(e, t) {
				g.load(k, e, t)
			},
			execCb: function(e, t, i, r) {
				return t.apply(r, i)
			},
			onScriptLoad: function(e) {
				if ("load" === e.type || ja.test((e.currentTarget || e.srcElement).readyState)) N = null, e = y(e), k.completeLoad(e.id)
			},
			onScriptError: function(e) {
				var t = y(e);
				if (!n(t.id)) return d(C("scripterror", "Script error for: " + t.id, e, [t.id]))
			}
		};
		k.require = k.makeRequire();
		return k
	}
	var g, x, y, D, I, E, N, J, s, O, ka = /(\/\*([\s\S]*?)\*\/|([^:]|^)\/\/(.*)$)/gm,
		la = /[^.]\s*require\s*\(\s*["']([^'"\s]+)["']\s*\)/g,
		Q = /\.js$/,
		ia = /^\.\//;
	x = Object.prototype;
	var K = x.toString,
		fa = x.hasOwnProperty,
		ha = Array.prototype.splice,
		z = !!("undefined" !== typeof window && "undefined" !== typeof navigator && window.document),
		ea = !z && "undefined" !== typeof importScripts,
		ja = z && "PLAYSTATION 3" === navigator.platform ? /^complete$/ : /^(complete|loaded)$/,
		Y = "undefined" !== typeof opera && "[object Opera]" === opera.toString(),
		F = {},
		q = {},
		R = [],
		M = !1;
	if ("undefined" === typeof define) {
		if ("undefined" !== typeof requirejs) {
			if (G(requirejs)) return;
			q = requirejs;
			requirejs = void 0
		}
		"undefined" !== typeof require && !G(require) && (q = require, require = void 0);
		g = requirejs = function(e, t, i, r) {
			var n, o = "_";
			!H(e) && "string" !== typeof e && (n = e, H(t) ? (e = t, t = i, i = r) : e = []);
			n && n.context && (o = n.context);
			(r = m(F, o)) || (r = F[o] = g.s.newContext(o));
			n && r.configure(n);
			return r.require(e, t, i)
		};
		g.config = function(e) {
			return g(e)
		};
		g.nextTick = "undefined" !== typeof setTimeout ? function(e) {
			setTimeout(e, 4)
		} : function(e) {
			e()
		};
		require || (require = g);
		g.version = "2.1.15";
		g.jsExtRegExp = /^\/|:|\?|\.js$/;
		g.isBrowser = z;
		x = g.s = {
			contexts: F,
			newContext: ga
		};
		g({});
		v(["toUrl", "undef", "defined", "specified"], function(e) {
			g[e] = function() {
				var t = F._;
				return t.require[e].apply(t, arguments)
			}
		});
		if (z && (y = x.head = document.getElementsByTagName("head")[0], D = document.getElementsByTagName("base")[0])) y = x.head = D.parentNode;
		g.onError = ca;
		g.createNode = function(e) {
			var t = e.xhtml ? document.createElementNS("http://www.w3.org/1999/xhtml", "html:script") : document.createElement("script");
			t.type = e.scriptType || "text/javascript";
			t.charset = "utf-8";
			t.async = !0;
			return t
		};
		g.load = function(e, t, i) {
			var r = e && e.config || {};
			if (z) return r = g.createNode(r, t, i), r.setAttribute("data-requirecontext", e.contextName), r.setAttribute("data-requiremodule", t), r.attachEvent && !(r.attachEvent.toString && 0 > r.attachEvent.toString().indexOf("[native code")) && !Y ? (M = !0, r.attachEvent("onreadystatechange", e.onScriptLoad)) : (r.addEventListener("load", e.onScriptLoad, !1), r.addEventListener("error", e.onScriptError, !1)), r.src = i, J = r, D ? y.insertBefore(r, D) : y.appendChild(r), J = null, r;
			if (ea) try {
				importScripts(i), e.completeLoad(t)
			} catch (n) {
				e.onError(C("importscripts", "importScripts failed for " + t + " at " + i, n, [t]))
			}
		};
		z && !q.skipDataMain && T(document.getElementsByTagName("script"), function(e) {
			y || (y = e.parentNode);
			if (I = e.getAttribute("data-main")) return s = I, q.baseUrl || (E = s.split("/"), s = E.pop(), O = E.length ? E.join("/") + "/" : "./", q.baseUrl = O), s = s.replace(Q, ""), g.jsExtRegExp.test(s) && (s = I), q.deps = q.deps ? q.deps.concat(s) : [s], !0
		});
		define = function(e, t, i) {
			var r, n;
			"string" !== typeof e && (i = t, t = e, e = null);
			H(t) || (i = t, t = null);
			!t && G(i) && (t = [], i.length && (i.toString().replace(ka, "").replace(la, function(e, i) {
				t.push(i)
			}), t = (1 === i.length ? ["require"] : ["require", "exports", "module"]).concat(t)));
			if (M) {
				if (!(r = J)) N && "interactive" === N.readyState || T(document.getElementsByTagName("script"), function(e) {
					if ("interactive" === e.readyState) return N = e
				}), r = N;
				r && (e || (e = r.getAttribute("data-requiremodule")), n = F[r.getAttribute("data-requirecontext")])
			}(n ? n.defQueue : R).push([e, t, i])
		};
		define.amd = {
			jQuery: !0
		};
		g.exec = function(b) {
			return eval(b)
		};
		g(q)
	}
})(this);
(function(e) {
	"use strict";
	var t = {
		enforceDefine: true,
		skipDataMain: false,
		baseUrl: "/_assets/js",
		paths: {
			backbone: "lib/backbone/backbone-1.1.2",
			marionette: "lib/backbone/marionette-2.2.0",
			bootstrap3: "/_assets/bootstrap/js/bootstrap.min",
			jquery: "lib/jquery/1.8.3.min",
			jqueryui: "lib/jquery/ui/1.10.4/jquery-ui",
			ckeditor: "lib/ckeditor/ckeditor",
			fancybox: "lib/fancybox/jquery.fancybox-1.3.4.pack",
			underscore: "lib/underscore-1.6.0",
			uuid: "lib/uuid",
			ZeroClipboard: "lib/ZeroClipboard/ZeroClipboard.min",
			sparkline: "lib/sparkline-2.1.2.min",
			sprintf: "lib/jquery/sprintf.min",
			cycle: "lib/jquery/cycle",
			lazyload: "lib/jquery/lazyLoad.min"
		},
		shim: {
			backbone: {
				deps: ["jquery", "underscore"],
				exports: "Backbone",
				init: function() {
					Backbone.emulateHTTP = true
				}
			},
			marionette: {
				deps: ["backbone"],
				exports: "Marionette"
			},
			bootstrap3: {
				deps: ["jquery"],
				exports: "jQuery"
			},
			jquery: {
				exports: "jQuery"
			},
			jqueryui: {
				deps: ["jquery"],
				exports: "jQuery",
				init: function() {
					i("/_assets/js/lib/jquery/ui/1.10.4/css/jquery-ui.css")
				}
			},
			ckeditor: {
				deps: ["jquery"],
				exports: "CKEDITOR"
			},
			fancybox: {
				deps: ["jquery"],
				exports: "jQuery"
			},
			underscore: {
				exports: "_"
			},
			uuid: {
				exports: function() {
					var e = uuid.noConflict();
					return function() {
						return e.v4.apply()
					}
				}
			},
			timepicker: {
				deps: ["jquery", "jqueryui"],
				exports: "jQuery"
			},
			cycle: {
				deps: ["jquery"],
				exports: "jQuery"
			},
			sprintf: {
				deps: ["jquery"],
				exports: "jQuery"
			},
			sparkline: {
				deps: ["jquery"],
				exports: "jQuery"
			},
			lazyload: {
				deps: ["jquery"],
				exports: "jQuery"
			}
		}
	};
	requirejs.config(t);
	(function() {
		var e = e();
		var i = {};
		if (window.location.hostname.indexOf("diaochapai") !== -1) {
			i = {
				jquery: "http://apps.bdimg.com/libs/jquery/1.8.3/jquery.min",
				backbone: "http://apps.bdimg.com/libs/backbone.js/1.1.2/backbone-min",
				bootstrap3: "http://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min",
				underscore: "http://apps.bdimg.com/libs/underscore.js/1.6.0/underscore-min"
			}
		}
		var r = {};
		for (var n in i) {
			var o = t["paths"][n];
			r[n] = [i[n], o]
		}
		requirejs.config({
			paths: r
		});
		if (e) {
			var a = t["baseUrl"];
			requirejs.config({
				baseUrl: e + a
			})
		}

		function e() {
			var e = window.location.hostname;
			if (!e) return "";
			var t = ["www.diaochapai.com", "www.diaochapai.net", "diaocha.xoyo.com", "test.www.diaochapai.net"];
			for (var i = 0, r = t.length; i < r; i++) {
				if (t[i] == e) {
					return "http://cdn.diaochapai.net"
				}
			}
			return ""
		}
	})();

	function i(e) {
		var t = document.createElement("link");
		t.type = "text/css";
		t.rel = "stylesheet";
		t.href = e;
		document.getElementsByTagName("head")[0].appendChild(t)
	}
	e.__ = function(e, t) {
		var i = window.dict || {};
		if (!i.hasOwnProperty(e)) return e;
		var r = i[e];
		if (!t) return r;
		for (var n in t) {
			var o = t[n];
			r = r.replace(new RegExp(":" + n, "g"), o)
		}
		return r
	};
	e.__loadDict = function(t, i) {
		i = i || document.documentElement.getAttribute("lang");
		if (!i) return false;
		for (var r = 0, n = t.length; r < n; r++) t[r] = "lang/" + i + "/" + t[r];
		var o = e.dict;
		if (!o) o = e.dict = {};
		requirejs(t, function() {
			for (var e = 0, t = arguments.length; e < t; e++) {
				var i = arguments[e];
				for (var r in i) o[r] = i[r]
			}
		})
	}
})(this);