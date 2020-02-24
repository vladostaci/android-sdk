//
// VersionControl.swift
//
// Generated by openapi-generator
// https://openapi-generator.tech
//

import Foundation


public struct VersionControl: Codable {

    public var update: String
    public var updateVersions: [UpdateVersion]
    public var newInVersion: Bool

    public init(update: String, updateVersions: [UpdateVersion], newInVersion: Bool) {
        self.update = update
        self.updateVersions = updateVersions
        self.newInVersion = newInVersion
    }

    public enum CodingKeys: String, CodingKey, CaseIterable { 
        case update
        case updateVersions = "update_versions"
        case newInVersion = "new_in_version"
    }

}