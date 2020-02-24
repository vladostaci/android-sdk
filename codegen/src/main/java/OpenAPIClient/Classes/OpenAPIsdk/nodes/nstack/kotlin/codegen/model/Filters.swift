//
// Filters.swift
//
// Generated by openapi-generator
// https://openapi-generator.tech
//

import Foundation


public struct Filters: Codable {

    public var natural: String
    public var dagger: String
    public var koi: String
    public var mandala: String
    public var rose: String
    public var hannya: String

    public init(natural: String, dagger: String, koi: String, mandala: String, rose: String, hannya: String) {
        self.natural = natural
        self.dagger = dagger
        self.koi = koi
        self.mandala = mandala
        self.rose = rose
        self.hannya = hannya
    }

}