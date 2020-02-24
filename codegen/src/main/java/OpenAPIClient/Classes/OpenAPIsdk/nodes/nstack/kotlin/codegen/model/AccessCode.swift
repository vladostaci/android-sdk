//
// AccessCode.swift
//
// Generated by openapi-generator
// https://openapi-generator.tech
//

import Foundation


public struct AccessCode: Codable {

    public var headerTitle: String
    public var content: String
    public var skip: String
    public var placeholder: String
    public var invalidTitle: String
    public var invalidMessage: String

    public init(headerTitle: String, content: String, skip: String, placeholder: String, invalidTitle: String, invalidMessage: String) {
        self.headerTitle = headerTitle
        self.content = content
        self.skip = skip
        self.placeholder = placeholder
        self.invalidTitle = invalidTitle
        self.invalidMessage = invalidMessage
    }

}