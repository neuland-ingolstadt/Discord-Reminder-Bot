const Discord = require('discord.js')
const schedule = require('node-schedule')
const config = require('./config.json')

const TOKEN = config.token
const GUILD = config.guild
const CHANNEL = config.channel
const CRON = config.cron
const MESSAGE = config.message
const DRY_RUN = config.dryRun

const client = new Discord.Client()

async function execute () {
  console.info('Executing job')

  const guild = await client.guilds.fetch(GUILD)
  if (!guild) {
    throw new Error(`Guild ${GUILD} not found`)
  }

  const channel = guild.channels.cache.get(CHANNEL)
  if (!channel) {
    throw new Error(`Channel ${CHANNEL} not found`)
  }

  if (DRY_RUN) {
    console.info(`Pretended to send message: ${MESSAGE}`)
  } else {
    await channel.send(MESSAGE)
    console.info(`Sent message: ${MESSAGE}`)
  }
}

client.on('ready', () => {
  console.info(`Logged in as ${client.user.tag}`)

  schedule.scheduleJob(CRON, async () => {
    try {
      execute()
    } catch (e) {
      console.error(e)
    }
  })
})

client.login(TOKEN)
