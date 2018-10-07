require 'em-websocket'
require_relative './argv_parser.rb'
require_relative './connection.rb'
require_relative './server.rb'

module RubyParker

  @@server = nil
  @@connects = []

  #------------------------------------------------------------------------
  # ● EM纤程模式执行
  #------------------------------------------------------------------------
  def self.em_fiber_run
    EM.run do
      Fiber.new do
        yield
        EM.stop
      end.resume
    end
  end

  #------------------------------------------------------------------------
  # ● 广播
  #------------------------------------------------------------------------
  def self.boardcast msg
    @@connects.each do |conn|
      conn.ws.send
    end
  end

  #------------------------------------------------------------------------
  # ● 查找 connection
  #------------------------------------------------------------------------
  def self.find_conn ws
    conns = @@connects.select do |conn|
      conn.ws === ws
    end
    conns[0]
  end

  #------------------------------------------------------------------------
  # ● 删除 connection
  #------------------------------------------------------------------------
  def self.remove_conn ws
    conns = @@connects.delete_if do |conn|
      conn.ws === ws
    end

  end

  #------------------------------------------------------------------------
  # ● [核心] 服务启动
  #------------------------------------------------------------------------

  def self.start_server argv

    EM.run {
      EM::WebSocket.run(:host => "0.0.0.0", :port => 8080, :debug => false) do |ws|
        ws.onopen { |handshake|

          @@connects << Connection.new(ws,handshake)

          ws.send "Hello Client!"
        }
        ws.onmessage { |msg|

          self.em_fiber_run do
            conn = self.find_conn(ws)
            Handler.new.on_message(conn,msg)
          end

        }

        ws.onclose {
          puts "WebSocket closed"
        }

        ws.onerror { |e|
          puts "Error: #{e.message}"
        }
      end
    }

  end

end


