module RubyPark

  module ArgvParser

    @@argv = {}

    module_function
    def parse! argv

      OptionParser.new do |opts|
        opts.banner = "swee框架使用参数如下"

        opts.on("-p", "--port PORT", Integer,
                "端口 (默认: 3000)") do |port|
          argv[:listen] = port
        end

        opts.on("-e", "--env ENV",
                "环境 development 或 production (默认为development)") do |e|
          options[:env] = e
        end
      end.parse!

    end
  end

end