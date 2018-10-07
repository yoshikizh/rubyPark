module RubyParker

  class Connection

    attr_reader :path
    attr_reader :query
    attr_reader :origin

    attr_reader :ws

    def initialize ws,handshake
      @ws = ws
      @path = handshake.path
      @query = handshake.query
      @origin = handshake.origin
    end
  end
end