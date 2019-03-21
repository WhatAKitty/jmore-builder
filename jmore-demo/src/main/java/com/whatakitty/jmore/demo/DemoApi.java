package com.whatakitty.jmore.demo;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * demo api
 *
 * @author WhatAKitty
 * @date 2019/02/19
 * @description
 **/
@RequestMapping("/api/demo")
public interface DemoApi {

    /**
     * @apiVersion 1.0.0
     * @api {post} /api/demo/status get status of this demo
     * @apiName GetStatus
     * @apiGroup DemoApi
     *
     * @apiSuccess {Number} code the code
     * @apiSuccess {String} msg response msg
     * @apiSuccess {Number} timestamp the happened time
     * @apiSuccess {Object} data the data to response
     * @apiSuccess (data) {String} ok the status of the demo app
     *
     * @apiSuccessExample response example
     * {
     *     "code": 0,
     *     "msg": "nice",
     *     "timestamp": 121123312121,
     *     "data": {
     *         "ok": "true"
     *     }
     * }
     */
    @PostMapping("/status")
    Object status();

    /**
     * @apiVersion 1.0.0
     * @api {post} /api/demo/exception throw exception
     * @apiName ThrowException
     * @apiGroup DemoApi
     *
     * @apiSuccess {Number} code the code
     * @apiSuccess {String} msg response msg
     * @apiSuccess {Number} timestamp the happened time
     * @apiSuccess {Object} data the data to response
     * @apiSuccess (data) {String} ok the status of the demo app
     *
     * @apiSuccessExample response example
     * {
     *     "code": 0,
     *     "msg": "nice",
     *     "timestamp": 121123312121,
     *     "data": {
     *         "ok": "true"
     *     }
     * }
     */
    @PostMapping("/exception")
    Object exception();

    /**
     * @apiVersion 1.0.0
     * @api {post} /api/demo/param1 post param1
     * @apiName Param1
     * @apiGroup DemoApi
     *
     * @apiParam {String} title the title of param
     * @apiParam {String} name the name of param
     * @apiParamExample request example
     * {
     *     "name": "bruce",
     *     "title": "hello"
     * }
     *
     * @apiSuccess {Number} code the code
     * @apiSuccess {String} msg response msg
     * @apiSuccess {Number} timestamp the happened time
     * @apiSuccess {Object} data the data to response
     * @apiSuccess (data) {String} ok the status of the demo app
     * @apiSuccessExample response example
     * {
     *     "code": 0,
     *     "msg": "nice",
     *     "timestamp": 121123312121,
     *     "data": {
     *         "ok": "true"
     *     }
     * }
     */
    @PostMapping("/param1")
    Object param1(@Validated @RequestBody DemoParam demoParam);

    /**
     * @apiVersion 1.0.0
     * @api {post} /api/demo/param2 post param2
     * @apiName Param2
     * @apiGroup DemoApi
     *
     * @apiParam {String} title the title of param
     * @apiParam {String} name the name of param
     * @apiParamExample request example
     * {
     *     "name": "bruce",
     *     "title": "hello"
     * }
     *
     * @apiSuccess {Number} code the code
     * @apiSuccess {String} msg response msg
     * @apiSuccess {Number} timestamp the happened time
     * @apiSuccess {Object} data the data to response
     * @apiSuccess (data) {String} ok the status of the demo app
     *
     * @apiSuccessExample response example
     * {
     *     "code": 0,
     *     "msg": "nice",
     *     "timestamp": 121123312121,
     *     "data": {
     *         "ok": "true"
     *     }
     * }
     */
    @PostMapping("/param2")
    Object param2(@Validated(DemoParam.DemoParamValidateGroup1.class) @RequestBody DemoParam demoParam);

}
